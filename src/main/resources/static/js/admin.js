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

        $('#console_update').on('click', function() {
            _this.console_update();
        })
    },

     console_manage : function() {
        admin.all_none();
        document.querySelector('#console_manage').style.display = "block";
        admin.list();
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
                     admin.list();
                 }).fail(function(error) {
                     alert(JSON.stringify(error));
                 });
             },

     console_update : function() {

                      var form = $('#console_update_form');
                      var formData = new FormData(form[0]);
                      var id = $('#admin_id').val();
                      $.ajax({
                          type: 'PUT',
                          url : '/admin/consoles/update/' + id,
                          data: formData,
                          dataType: 'json',
                          processData: false,
                          contentType: false,
                      }).done(function() {
                          alert('콘솔기종이 등록되었습니다');
                          document.querySelector('#console_update_cancel').click();
                          admin.list();
                      }).fail(function(error) {
                          alert(JSON.stringify(error));
                      });
                  },

     list : function() {

             $.ajax({
                 type : 'GET',
                 url : '/admin/consoles/consoleList',
                 dataType : 'json',
             }).done(function(success) {
                $('#conList').children().remove();
                var consoleData = success;
                for(var i in consoleData) {
                     $('#conList').append('<div class=\"col-lg-4 col-sm-4\">'
                        + '<div class=\"single_product_item text-center\" onclick=\"admin.view(' + consoleData[i].id + ');\">'
                        +       '<img src=\"/display/'+consoleData[i].img_num+'\" alt=\"#\" class=\"img-fluid\">'
                        +       '<h3> ' + consoleData[i].edition + ' </h3>'
                        +       '<p>' + admin.numberWithCommas(consoleData[i].c_price) + '원</p>'
                        +       '</div>'
                        +  '</div>');
                }
             }).fail(function(error) {
                 alert('게시글을 불러올수 없습니다');
             });
         },

//     view_modal : function() {
//        data-toggle="modal" data-target="#console_insert_modal"
//     },
//
     view : function(id) {
         $.ajax({
                         type : 'GET',
                         url : '/admin/consoles/view/' + id,
                         dataType : 'json',
                     }).done(function(success) {
                        var consoleData = success;
                        $('#up_manufact_nm').val(consoleData.manufact);
                        $('#_up_edition_nm').val(consoleData.edition);
                        $('#up_c_price').val(consoleData.c_price);
                        $('#admin_id').val(consoleData.id);
                        $("#console_update_modal").modal();
                     }).fail(function(error) {
                         alert('게시글을 불러올수 없습니다');
                     });
     },

     numberWithCommas : function (x) {
      	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
     },
}

admin.init();