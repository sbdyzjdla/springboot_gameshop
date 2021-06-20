var cart = {
    init : function() {
        var _this = this;

        $('#add_cart').on('click', function() {
            _this.cart_insert();
        })
    },

    cart_insert : function() {

         var Data = {
            "id" : $('#id').val(),
            "quantity" : $('#p_quantity').val(),
         };

         $.ajax({
             type: 'POST',
             url : '/cart/save/',
             data: Data,
             dataType: 'json',
         }).done(function() {
             alert('장바구니에 상품이 추가 되었습니다');
         }).fail(function(error) {
             alert(JSON.stringify(error));
         });
    },``
}

cart.init();
