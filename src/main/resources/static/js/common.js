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
}
common.init();


