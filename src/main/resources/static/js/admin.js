var admin = {
    init : function() {
        var _this = this;

        $('#console_insert_bt').on('click', function() {
            _this.console_insert();
        })

        $('#product_insert_bt').on('click', function() {
            _this.product_insert();
        })
    },

     console_insert : function() {
        admin.all_none();
        document.querySelector('#console_insert').style.display = "block";
     },

     product_insert : function() {
        admin.all_none();
        document.querySelector('#product_insert').style.display = "block";
     },

     all_none : function() {
        document.querySelector('#console_insert').style.display = "none";
        document.querySelector('#product_insert').style.display = "none";
     },
}

admin.init();