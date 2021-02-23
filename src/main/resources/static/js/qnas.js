var qnas = {
    init : function() {
        var _this = this;

        $('#qna-save').on('click', function() {
            _this.save();
        })
    },

    save : function() {
        var data = {
            title : $('#title').val(),
            author : $('#name').val(),
            content : $('#content').val(),
            reply_state : '미답변',
        };

        $.ajax({
            type: 'POST',
            url : '/api/v1/qnas',
            dataType : 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function() {
            alert('글이 등록되었습니다');

        }).fail(function(error) {
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
                url : '/api/v1/qnas/qnaslist',
                dataType : 'json',
            }).done(function(success) {
            $('#qnasTable').children().remove();
            var qnaslist = success;
                for(var i in qnaslist) {
                    $('#qnasTable').append('<tr>'
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
}


qnas.init();