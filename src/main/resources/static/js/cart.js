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
             if (confirm("장바구니로 이동하시겠습니까??") == true){ location.href ="/cart";
             } else {false}
         }).fail(function(error) {
             alert(JSON.stringify(error));
         });
    },

    cart_update : function(id, ths) {
        var quantity = ths.parentElement.parentElement.children[3].children[0].children[1].value;
        var Data = {
            "quantity" : quantity,
        };

        $.ajax({
            type : 'PUT',
            url : '/cart/update/'+id,
            data : Data,
            dataType : 'json',
        }).done(function() {
            alert('수량이 수정되었습니다');
            location.href='/cart';
        }).fail(function(error) {
            alert('수정에 실패하였습니다')
            alert(JSON.stringify(error))
        })
    },

    cart_update_calc : function() {
        var cart_length = $("tr[name=cartList]").length;
        var total_price =0;
        for(var i=0; i<cart_length; ++i) {
            var sum_price = document.querySelector('#cartBody').children[i].children[5].children[0].textContent;
            sum_price = sum_price.toString().replace('원', '');
            total_price += Number(sum_price);
        }
        total_price += '원';
        document.querySelector('#cartBody').children[cart_length].children[5].children[0].innerHTML = total_price;
    }
}
cart.init();