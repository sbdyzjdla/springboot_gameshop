var common = {

    init : function() {
            var _this = this;
        },
    numberWithCommas : function (x) {
          	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
         },
    initCart : function() {
        var cart_length = $("tr[name=cartList]").length;
            for(var i=0; i<cart_length; ++i) {
                var p_price = document.querySelector('#cartBody').children[i].children[2].children[0].textContent;
                document.querySelector('#cartBody').children[i].children[2].children[0].innerHTML
                                   = common.numberWithCommas(p_price);
                var sum_price = document.querySelector('#cartBody').children[i].children[5].children[0].textContent;
                document.querySelector('#cartBody').children[i].children[5].children[0].innerHTML
                   = common.numberWithCommas(sum_price);
            }
            var total_price = document.querySelector('#cartBody').children[cart_length].children[5].children[0].innerHTML;
            document.querySelector('#cartBody').children[cart_length].children[5].children[0].innerHTML
                = common.numberWithCommas(total_price);
    },

    initOrder : function() {
            var cart_length = $("tr[name=cartList]").length;
            var total_price = 0;
                for(var i=0; i<cart_length; ++i) {
                    var p_price = document.querySelector('#orderBody').children[i].children[1].children[0].textContent;
                    document.querySelector('#orderBody').children[i].children[1].children[0].innerHTML
                                       = common.numberWithCommas(p_price);
                    var sum_price = document.querySelector('#orderBody').children[i].children[3].children[0].textContent;
                    total_price += Number(sum_price.toString().replace('원', ''));
                    document.querySelector('#orderBody').children[i].children[3].children[0].innerHTML
                       = common.numberWithCommas(sum_price);
                }
                document.querySelector('#pay_amount').value = total_price;
                total_price += '원';
                document.querySelector('#amount').innerHTML = common.numberWithCommas(total_price);
//                var total_price = document.querySelector('#orderBody').children[cart_length].children[5].children[0].innerHTML;
//                document.querySelector('#orderBody').children[cart_length].children[5].children[0].innerHTML
//                    = common.numberWithCommas(total_price);
        },
}
common.init();


