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
    },

    cart_selectAll : function(selectAll) {
        const cart_checkboxes = document.getElementsByName('cart_check');

        cart_checkboxes.forEach((cart_check) => {
            cart_check.checked = selectAll.checked;
        })
        cart.cart_selectCalc();
    },

    cart_selectCalc : function() {

            var cart_length = cart_length = $("tr[name=cartList]").length;
                var total_price = 0;
                var list_price = [];
                for(var i=0; i<cart_length; ++i) {
                    if(document.getElementsByName('cart_check')[i+1].checked) {
                        var sum_price = document.querySelector('#cartBody').children[i].children[5].children[0].textContent;
                        sum_price = sum_price.toString().replace('원', '');
                        sum_price = sum_price.toString().replace(/,/gi, '');
                        list_price.push(Number(sum_price));
                        sum_price = 0;
                    }
                }
                for(var i=0; i<list_price.length; ++i) {
                    total_price += list_price[i];
                }
                total_price = total_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total_price += '원';
                document.querySelector('#cartBody').children[cart_length].children[5].children[0].innerHTML = total_price;
    },


    cart_delete : function() {
        if (confirm("장바구니에서 삭제 하시겠습니까??") == true)
            {
                var cart_length = cart_length = $("tr[name=cartList]").length;
                var id = 0;
                var list_checked = [];
                for(var i=0; i<cart_length; ++i) {
                    if(document.getElementsByName('cart_check')[i+1].checked) {
                        id = document.querySelector('#cartBody').children[i].children[6].value;
                        list_checked.push(id);
                    }
                }
                //ajax
                var Data = { "list_checked" : list_checked};
                $.ajax({
                    type : 'DELETE',
                    url : '/cart/del/',
                    dataType : 'json',
                    data : Data,
                }).done(function() {
                    //alert('장바구니에서 삭제되었습니다.');
                    location.href='/cart';
                }).fail(function(error) {
                    alert('수정에 실패하였습니다')
                    alert(JSON.stringify(error))
                })
        } else {false}
        //location.href='/cart';
    },

    cart_order : function() {
            if (confirm("선택하신 상품을 주문 하시겠습니까??") == true)
                {
                    var cart_length = cart_length = $("tr[name=cartList]").length;
                    var id = 0;
                    var list_checked = [];
                    for(var i=0; i<cart_length; ++i) {
                        if(document.getElementsByName('cart_check')[i+1].checked) {
                            id = document.querySelector('#cartBody').children[i].children[6].value;
                            list_checked.push(id);
                        }
                    }
                    //ajax
                    //var Data = { "list_checked" : list_checked};

                    $('#list_checked_id').val(list_checked);
                    console.log($('#list_checked_id').val());
                    $("#cart_order").submit();
//                    $.ajax({
//                        type : 'GET',
//                        url : '/order/',
//                        dataType : 'json',
//                        data : Data,
//                    }).done(function() {
//                        //alert('장바구니에서 삭제되었습니다.');
//                    }).fail(function(error) {
//                        alert('주문에 실패하였습니다')
//                        alert(JSON.stringify(error))
//                    })
            } else {false}
            //location.href='/cart';
        },

        order_one : function() {
                    if (confirm("선택하신 상품을 주문 하시겠습니까??") == true)
                        {
                            //ajax
                            var id = $('#id').val();
                            var quantity = $('#p_quantity').val();
                            location.href='/order/one/'+id+'/'+quantity;
                    } else {false}
                },
}
cart.init();