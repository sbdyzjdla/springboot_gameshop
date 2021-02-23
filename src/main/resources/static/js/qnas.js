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

    list : function() {

            $.ajax({
                type : 'GET',
                url : '/api/v1/qnas/qnaslist',
                dataType : 'json',
            }).done(function() {
                alert('게시글 성공');
            }).fail(function(error) {
                alert('게시글을 불러올수 없습니다');
            });
        },
}


qnas.init();