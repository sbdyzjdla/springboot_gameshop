var product = {
    init : function() {
        var _this = this;

        $('#qna-save').on('click', function() {
            _this.list_nin_console();
        })

        $('#more_consoles').on('click', function(e) {
            e.preventDefault();
            _this.list_consoles_more();
        })

        $('#more_titles').on('click', function(e) {
            e.preventDefault();
            _this.list_titles_more();
        })

    },

    list_console : function() {
        var url = $('#products_url').val();
        $.ajax({
            type : 'GET',
            url : '/api/v1/consoles/' + url ,
            dataType : 'json',
        }).done(function(success) {
        $('#product_list').children().remove();
        var product_list = success;
            for(var i in product_list.content) {
                 $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                     + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list.content[i].id + ');\">'
                     +       '<img src=\"/display/'+product_list.content[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\" style=\"cursor:pointer\">'
                     +       '<h3 style=\"cursor:pointer\"> ' + product_list.content[i].p_name + ' </h3>'
                     +       '<p style=\"cursor:pointer\">' + product_list.content[i].p_price + '원</p>'
                     +       '</div>'
                     +  '</div>');
            }
            $('#more_consoles').children().remove();
            if(success.last == false) {
                $('#more_consoles').append(
                    '<a href=\"#\" class=\"btn_3 col-md-2\">더보기</a>'
                    + '<input type=\"hidden\" id=\"products_page\" value=\"1\">'
                );
                document.querySelector('#products_page').value = success.number+1;
            }
        }).fail(function(error) {
            alert('게시글을 불러올수 없습니다');
        });
    },

    list_consoles_more : function() {
        var p_num = $('#products_page').val();
        var url = $('#products_url').val();
        $.ajax({
            type : 'GET',
            url : '/api/v1/consoles/'+ url + p_num,
            dataType : 'json',
        }).done(function(success) {
        var product_list = success;
            for(var i in product_list.content) {
                 $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                     + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list.content[i].id + ');\">'
                     +       '<img src=\"/display/'+product_list.content[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\" style=\"cursor:pointer\">'
                     +       '<h3 style=\"cursor:pointer\"> ' + product_list.content[i].p_name + ' </h3>'
                     +       '<p style=\"cursor:pointer\">' + product_list.content[i].p_price + '원</p>'
                     +       '</div>'
                     +  '</div>');
            }
            $('#more_consoles').children().remove();
            if(success.last == false) {
                $('#more_consoles').append(
                    '<a href=\"#\" class=\"btn_3 col-md-2\">더보기</a>'
                    + '<input type=\"hidden\" id=\"products_page\" value=\"'+ success.number + 1 + '\">'
                );
                document.querySelector('#products_page').value = success.number+1;
            }
        }).fail(function(error) {
            alert('게시글을 불러올수 없습니다');
        });
    },

    list_titles : function() {
        var url = $('#products_url').val();
        $.ajax({
            type : 'GET',
            url : '/api/v1/titles/' + url ,
            dataType : 'json',
        }).done(function(success) {
        $('#product_list').children().remove();
        var product_list = success;
            for(var i in product_list.content) {
                 $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                     + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list.content[i].id + ');\">'
                     +       '<img src=\"/display/'+product_list.content[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\" style=\"cursor:pointer\">'
                     +       '<h3 style=\"cursor:pointer\"> ' + product_list.content[i].p_name + ' </h3>'
                     +       '<p style=\"cursor:pointer\">' + product_list.content[i].p_price + '원</p>'
                     +       '</div>'
                     +  '</div>');
            }
            $('#more_titles').children().remove();
            if(success.last == false) {
                $('#more_titles').append(
                    '<a href=\"#\" class=\"btn_3 col-md-2\">더보기</a>'
                    + '<input type=\"hidden\" id=\"products_page\" value=\"1\">'
                );
                document.querySelector('#products_page').value = success.number+1;
            }
        }).fail(function(error) {
            alert('게시글을 불러올수 없습니다');
        });
    },

    list_titles_more : function() {
        var p_num = $('#products_page').val();
        var url = $('#products_url').val();
        $.ajax({
            type : 'GET',
            url : '/api/v1/titles/'+ url + p_num,
            dataType : 'json',
        }).done(function(success) {
        var product_list = success;
            for(var i in product_list.content) {
                 $('#product_list').append('<div class=\"col-lg-4 col-sm-4\">'
                     + '<div class=\"single_product_item text-center\" onclick=\"product.view(' + product_list.content[i].id + ');\">'
                     +       '<img src=\"/display/'+product_list.content[i].img_num+'\" alt=\"#\" class=\"img-fluid-list\" style=\"cursor:pointer\">'
                     +       '<h3 style=\"cursor:pointer\"> ' + product_list.content[i].p_name + ' </h3>'
                     +       '<p style=\"cursor:pointer\">' + product_list.content[i].p_price + '원</p>'
                     +       '</div>'
                     +  '</div>');
            }
            $('#more_titles').children().remove();
            if(success.last == false) {
                $('#more_titles').append(
                    '<a href=\"#\" class=\"btn_3 col-md-2\">더보기</a>'
                    + '<input type=\"hidden\" id=\"products_page\" value=\"'+ success.number + 1 + '\">'
                );
                document.querySelector('#products_page').value = success.number+1;
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