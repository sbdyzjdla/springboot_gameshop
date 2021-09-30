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

        $('#qnas_search_btn').on('click', function() {
            _this.search();
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
    },

    search : function() {
       var search = $('#qnas_search').val();
       location.href = '/board/1/' + search;
    },

    commentSave : function(e) {
        console.log(e);
        e.preventDefault();
        var Data = {
            'qnas_id' : document.querySelector('#id').value,
            'user_id' : document.querySelector('#userId').value,
            'content' : document.querySelector('#comment').value
        }
        $.ajax({
            type: 'POST',
            url : '/comment',
            data: JSON.stringify(Data),
            processData: false,
            contentType: 'application/json; charset=utf-8',
        }).done(function() {
            qnas.commentView();
            document.querySelector('#comment').value = '';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    commentView : function() {
            var qnas_id = document.querySelector('#id').value;
            $.ajax({
                type: 'GET',
                url : '/comment/' + qnas_id,
                processData: false,
                contentType: 'application/json; charset=utf-8',
            }).done(function(success) {
                $('#comments-area').children().remove();
                console.log(success);
                $('#comment_size2').val('aa');
                for(var i in success) {
                    if(success[i].role == 'ADMIN') {
                        success[i].name = '관리자';
                    }
                    var content = '';
                    content +=( '<div class=\"comment-list\">'
                                +'<div class=\"single-comment justify-content-between d-flex\">'
                                +    '<div class=\"user justify-content-between d-flex\">'
                                +    '<div class=\"thumb\">'
                                +        '<img src=\"' + success[i].picture + ' \" alt=\"\">'
                                +    '</div>'
                                +    '<div class=\"desc\">'
                                +        '<p class=\"comment\">'
                                +         success[i].content
                                +        '</p>'
                                +        '<div class=\"d-flex justify-content-between\">'
                                +            '<div class=\"d-flex align-items-center\">'
                                +                '<h5>'
                                +                    '<a href=\"#\">'+ success[i].name +'</a>'
                                +                '</h5>'
                                +                '<p class=\"date\">'+ success[i].comment_date +'</p>'
                                +            '</div>'
                                +            '<div class=\"reply-btn'+ success[i].user_id +'\"'
                                +             'onclick=\"qnas.commentUpdateView(' + success[i].comment_id + ',event)\">'
                                +            '</div>'
                                +            '<div class=\"del-btn'+ success[i].user_id +'\"'
                                +             'onclick=\"qnas.commentDel(' + success[i].comment_id + ',event)\">'
                                +               '<input type=\"hidden\" value=\"' + success[i].comment_id + '\" />'
                                +            '</div>'
                                +        '</div>'
                                +    '</div>'
                            +    '</div>'
                            +   '</div>'
                        +    '</div>' );
                $('#comments-area').append(content);
                };
                $('.reply-btn'+document.querySelector('#userId').value).append(
                    '<a href=\"#\" class=\"btn-reply text-uppercase\">수정</a>'
                    );
                $('.del-btn'+document.querySelector('#userId').value).append(
                    '<a href=\"#\" class=\"del-reply text-uppercase\">삭제</a>'
                    );

            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        },

         commentUpdate : function(e) {
            e.preventDefault();
            var Data = {
                'qnas_id' : document.querySelector('#id').value,
                'user_id' : document.querySelector('#userId').value,
                'content' : document.querySelector('#comment').value
            }
            $.ajax({
                type: 'POST',
                url : '/comment',
                data: JSON.stringify(Data),
                processData: false,
                contentType: 'application/json; charset=utf-8',
            }).done(function() {
                qnas.commentView();
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        },

        commentUpdateView : function(id, e) {
            e.preventDefault();
            alert('update');
        },

        commentDel : function(id, e) {
            e.preventDefault();
            if(confirm("댓글을 삭제하시겠습니까?") == true) {
                    $.ajax({
                        type: 'DELETE',
                        url : '/comment/'+id,
                        dataType : 'json',
                        contentType:'application/json; charset=utf-8',
                    }).done(function() {
                        qnas.commentView();
                    }).fail(function(error) {
                        alert('댓글 삭제에 실패하였습니다');
                        alert(JSON.stringify(error));
                    });
                }
            },

}

qnas.init();