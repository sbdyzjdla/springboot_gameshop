var admin = {
    init : function() {
        var _this = this;

        $('#console_manage_bt').on('click', function() {
            _this.console_manage();
        })

        $('#product_manage_bt').on('click', function() {
            _this.product_manage();
        })

        $('#console_insert').on('click', function() {
            _this.console_insert();
        })
    },

     console_manage : function() {
        admin.all_none();
        document.querySelector('#console_manage').style.display = "block";
     },

     product_manage : function() {
        admin.all_none();
        document.querySelector('#product_manage').style.display = "block";
     },

     all_none : function() {
        document.querySelector('#console_manage').style.display = "none";
        document.querySelector('#product_manage').style.display = "none";
     },

     console_insert : function() {

                 var form = $('#console_insert_form');
                 var formData = new FormData(form[0]);

                 $.ajax({
                     type: 'POST',
                     url : '/admin/consoles/save',
                     data: formData,
                     dataType: 'json',
                     processData: false,
                     contentType: false,
                 }).done(function() {
                     alert('콘솔기종이 등록되었습니다');
                     document.querySelector('#console_insert_cancel').click();
                 }).fail(function(error) {
                     alert(JSON.stringify(error));
                 });
             },
}

admin.init();