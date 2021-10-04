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
                                +        '<div class=\"d-flex justify-content\">'
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
                                +'<aside class=\"single_sidebar_widget newsletter_widget md-12\" style=\"display:none\">'
                             +     '<h4 class=\"widget_title\">댓글수정</h4>'
                             +     '<form action=\"#\">'
                             +        '<div class=\"form-group\">'
                             +           '<textarea class=\"form-control w-100\" name=\"comment\" id=\"comment'+ success[i].comment_id +'\" cols=\"50\" rows=\"2\" placeholder=\"댓글을 작성하세요\" style=\"margin-top: 0px; margin-bottom: 0px; height: 93px;\"></textarea>'
                             +        '</div>'
                             +     '</form>'
                             +      '<a href=\"#\" class=\"genric-btn info\" onclick=\"qnas.commentUpdate('+ success[i].comment_id +', event)\">수정</a>'
                             +      '<a href=\"#\" class=\"genric-btn danger\">닫기</a>'
                             +  '</aside>'
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
                $('.aaa').append(
                                    '<aside class=\"single_sidebar_widget newsletter_widget md-12\">'
                                    +     '<h4 class=\"widget_title\">댓글수정</h4>'
                                    +     '<form action=\"#\">'
                                    +        '<div class=\"form-group\">'
                                    +           '<textarea class=\"form-control w-100\" name=\"comment\" id=\"comment\" cols=\"50\" rows=\"2\" placeholder=\"댓글을 작성하세요\" style=\"margin-top: 0px; margin-bottom: 0px; height: 93px;\"></textarea>'
                                    +        '</div>'
                                    +        '<button class=\"button rounded-0 primary-bg text-white w-100 btn_1\" type=\"submit\">수정</button>'
                                    +     '</form>'
                                    +  '</aside>'
                                    );
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        },

         commentUpdate : function(comment_id,e) {
            e.preventDefault();
            var Data = {
                'id' : comment_id,
                'content' : document.querySelector('#comment'+comment_id).value
            }
            console.log(Data);
            $.ajax({
                type: 'PUT',
                url : '/comment',
                data : Data,
            }).done(function() {
                qnas.commentView();
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        },

        commentUpdateView : function(id, e) {
            e.preventDefault();
            var classN = document.getElementsByClassName('.single_sidebar_widget newsletter_widget md-12');
            for(var i=0; i<classN.length; ++i) {
                classN[i].style.display = 'none';
            }
            aaa = e.target.parentNode.parentNode;
            aaa.nextSibling.style.display = 'block';
            console.log(e.target.parentNode.parentNode)
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
var aaa;
qnas.init();