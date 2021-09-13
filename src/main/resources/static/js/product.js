var product = {
    init : function() {
        var _this = this;

        $('#qna-save').on('click', function() {
            _this.list_nin_console();
        })

        $('#more_products').on('click', function(e) {
            e.preventDefault();
            _this.list_nin_console_more();
        })

    },

    list_nin_console : function() {

        $.ajax({
            type : 'GET',
            url : '/api/v1/consoles/nintendoCList/',
            dataType : 'json',
        }).done(function(success) {
        $('#product_list').children().remove();
        var product_list = success;
            for(var i in product_list.content) {
                 $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                     + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list.content[i].id + ');\">'
                     +       '<img src=\"/display/'+product_list.content[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\">'
                     +       '<h3> ' + product_list.content[i].p_name + ' </h3>'
                     +       '<p>' + product_list.content[i].p_price + '원</p>'
                     +       '</div>'
                     +  '</div>');
            }
            $('#more_products').children().remove();
            if(success.last == false) {
                $('#more_products').append(
                    '<a href=\"#\" class=\"btn_3 col-md-2\">더보기</a>'
                    + '<input type=\"hidden\" id=\"products_page\" value=\"1\">'
                );
                document.querySelector('#products_page').value = success.number+1;
            }
        }).fail(function(error) {
            alert('게시글을 불러올수 없습니다');
        });
    },

    list_nin_console_more : function() {
        var p_num = $('#products_page').val();
        $.ajax({
                    type : 'GET',
                    url : '/api/v1/consoles/nintendoCList/' + p_num,
                    dataType : 'json',
                }).done(function(success) {
                var product_list = success;
                    for(var i in product_list.content) {
                         $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                             + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list.content[i].id + ');\">'
                             +       '<img src=\"/display/'+product_list.content[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\">'
                             +       '<h3> ' + product_list.content[i].p_name + ' </h3>'
                             +       '<p>' + product_list.content[i].p_price + '원</p>'
                             +       '</div>'
                             +  '</div>');
                    }
                    $('#more_products').children().remove();
                    if(success.last == false) {
                        $('#more_products').append(
                            '<a href=\"#\" class=\"btn_3 col-md-2\">더보기</a>'
                            + '<input type=\"hidden\" id=\"products_page\" value=\"'+ success.number + 1 + '\">'
                        );
                        document.querySelector('#products_page').value = success.number+1;
                    }
                }).fail(function(error) {
                    alert('게시글을 불러올수 없습니다');
                });
    },

    list_ps5_console : function() {

            $.ajax({
                type : 'GET',
                url : '/api/v1/consoles/ps5CList',
                dataType : 'json',
            }).done(function(success) {
            $('#product_list').children().remove();
            var product_list = success;
                for(var i in product_list) {
                     $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                         + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list[i].id + ');\">'
                         +       '<img src=\"/display/'+product_list[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\">'
                         +       '<h3> ' + product_list[i].p_name + ' </h3>'
                         +       '<p>' + product_list[i].p_price + '원</p>'
                         +       '</div>'
                         +  '</div>');
                }
            }).fail(function(error) {
                alert('게시글을 불러올수 없습니다');
            });
        },

    list_ns_software : function() {

            $.ajax({
                type : 'GET',
                url : '/api/v1/titles/nsSoftList',
                dataType : 'json',
            }).done(function(success) {
            $('#product_list').children().remove();
            var product_list = success;
                for(var i in product_list) {
                     $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                         + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list[i].id + ');\">'
                         +       '<img src=\"/display/'+product_list[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\">'
                         +       '<h3> ' + product_list[i].p_name + ' </h3>'
                         +       '<p>' + product_list[i].p_price + '원</p>'
                         +       '</div>'
                         +  '</div>');
                }
            }).fail(function(error) {
                alert('게시글을 불러올수 없습니다');
            });
        },

    list_ps5_software : function() {

                $.ajax({
                    type : 'GET',
                    url : '/api/v1/titles/ps5SoftList',
                    dataType : 'json',
                }).done(function(success) {
                $('#product_list').children().remove();
                var product_list = success;
                    for(var i in product_list) {
                         $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                             + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list[i].id + ');\">'
                             +       '<img src=\"/display/'+product_list[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\">'
                             +       '<h3> ' + product_list[i].p_name + ' </h3>'
                             +       '<p>' + product_list[i].p_price + '원</p>'
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