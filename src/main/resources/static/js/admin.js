var admin = {
    init : function() {
        var _this = this;

        $('#console_manage_bt').on('click', function() {
            _this.console_manage();
        })

        $('#product_manage_bt').on('click', function() {
            _this.product_insert();
        })

        $('#console_insert').on('click', function() {
            _this.console_manage();
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

     save : function() {
                 var data = {
                     reply_state : '미답변',
                 };
                 var form = $('#commentForm');
                 var formData = new FormData(form[0]);
                   formData.append("reply_state", data.reply_state);

                 $.ajax({
                     type: 'POST',
                     url : '/api/v1/qnas',
                     data: formData,
                     dataType: 'json',
                     processData: false,
                     contentType: false,
                 }).done(function() {
                     alert('글이 등록되었습니다');
                     qnas.listInit();
                 }).fail(function(error) {
                     alert(JSON.stringify(error));
                 });
             },
}

admin.init();