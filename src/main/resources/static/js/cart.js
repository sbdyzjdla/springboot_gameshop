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
    },

    cart_count : function() {

        $("tr[name^='cartList']")

            var min = el.attr('min') || false;
            var max = el.attr('max') || false;

            var els = {};

            els.dec = el.prev();
            els.inc = el.next();

            el.each(function() {
              init($(this));
            });

            function init(el) {

              els.dec.on('click', decrement);
              els.inc.on('click', increment);

              function decrement() {
                var value = el[0].value;
                value--;
                if(!min || value >= min) {
                  el[0].value = value;
                }
              }

              function increment() {
                var value = el[0].value;
                value++;
                if(!max || value <= max) {
                  el[0].value = value++;
                }
              }
            }
          }
        })();
    },
}

cart.init();