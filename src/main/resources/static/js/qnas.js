var qnas = {
    init : function() {
        var _this = this;

        $('#qna-save').on('click', function() {
            _this.save();
        })

        $('#qna-update').on('click', function() {
            _this.update();
        })

        $('#qna-delete').on('click', function() {
            _this.delete();
        })
    },

     save : function() {
            var data = {
                reply_state : '미답변',
            };
            var form = $('#commentForm');
            var formData = new FormData(form[0]);
              formData.append("reply_state", data.reply_state);

            $.ajax({
                type: 'POST',
                url : '/api/v1/qnas',
                data: formData,
                dataType: 'json',
                processData: false,
                contentType: false,
            }).done(function() {
                alert('글이 등록되었습니다');
                //qnas.listInit();
                location.href="/board/1"
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        },

    update : function() {
        var form = $('#commentForm');
        var formData = new FormData(form[0]);
        var id = $('#id').val();
        $.ajax({
            type: 'PUT',
            url : '/api/v1/qnas/'+id,
            data: formData,
            dataType : 'json',
            processData: false,
            contentType: false,
        }).done(function() {
            alert('글이 수정되었습니다');
            //qnas.listInit();
            location.href="/board/1"
        }).fail(function(error) {
            alert('게시글 수정에 실패하였습니다');
            alert(JSON.stringify(error));
        });
    },

    delete : function() {
        var id = $('#id').val();
        $.ajax({
            type: 'DELETE',
            url : '/api/v1/qnas/'+id,
            dataType : 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다');
            qnas.listInit();
        }).fail(function(error) {
            alert('게시글 삭제에 실패하였습니다');
            alert(JSON.stringify(error));
        });
    },

    listInit : function() {
        location.href = '/board';
        list();
    },

    list : function() {

        $.ajax({
            type : 'GET',
            url : '/api/v1/qnaslist',
            dataType : 'json',
        }).done(function(success) {
        $('#qnasTable').children().remove();
        var qnaslist = success;
            for(var i in qnaslist) {
                $('#qnasTable').append('<tr onclick=\"qnas.viewInit(' + qnaslist[i].id + ');\">'
                + '<th scope=\"row\">'+ qnaslist[i].id +'</th>'
                + '<td>'+ qnaslist[i].reply_state +'</td>'
                + '<td>'+ qnaslist[i].title +'</td>'
                + '<td>'+ qnaslist[i].author +'</td>'
                + '<td>'+ qnaslist[i].createdDate +'</td>'
              + '</tr>');
          }
        }).fail(function(error) {
            alert('게시글을 불러올수 없습니다');
        });
    },

    viewInit : function(id) {
        location.href = '/board/view_board/' + id;
        //qnas.viewQnas(id);
    },

    viewQnas : function() {
         $.ajax({
            type : 'GET',
            url : '/api/v1/qnaslist',
            dataType : 'json',
        }).done(function(success) {
            alert('게시글 성공!!');
        }).fail(function(error) {
            alert('게시글을 불러올수 없습니다');
        });
    }
}

qnas.init();