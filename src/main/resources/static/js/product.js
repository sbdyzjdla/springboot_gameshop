var product = {
    init : function() {
        var _this = this;

        $('#qna-save').on('click', function() {
            _this.list_nin_console();
        })
    },

    list_nin_console : function() {

        $.ajax({
            type : 'GET',
            url : '/api/v1/consoles/nintendoCList',
            dataType : 'json',
        }).done(function(success) {
        $('#nintendo_list').children().remove();
        var nintendo_list = success;
            for(var i in nintendo_list) {
                 $('#nintendo_list').append('<div class=\"col-lg-4 col-sm-4\">'
                     + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + nintendo_list[i].id + ');\">'
                     +       '<img src=\"/display/'+nintendo_list[i].img_num+'\" alt=\"#\" class=\"img-fluid\">'
                     +       '<h3> ' + nintendo_list[i].p_name + ' </h3>'
                     +       '<p>' + nintendo_list[i].p_price + '원</p>'
                     +       '</div>'
                     +  '</div>');
            }
        }).fail(function(error) {
            alert('게시글을 불러올수 없습니다');
        });
    },

    view : function(id) {
            location.href = '/products/view/' + id;
        },
}

product.init();