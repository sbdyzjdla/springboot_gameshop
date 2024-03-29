var admin = {
    init : function() {
        var _this = this;

        $('#console_manage_bt').on('click', function() {
            _this.console_manage();
        })

        $('#title_manage_bt').on('click', function() {
            _this.title_manage();
        })

        $('#console_insert').on('click', function() {
            _this.console_insert();
        })

        $('#console_update').on('click', function() {
            _this.console_update();
        })

        $('#console_delete').on('click', function() {
                    _this.console_delete();
        })

        $('#title_insert').on('click', function() {
            _this.title_insert();
        })

        $('#title_update').on('click', function() {
            _this.title_update();
        })

        $('#title_delete').on('click', function() {
            _this.title_delete();
        })

    },

     console_manage : function() {
        admin.all_none();
        document.querySelector('#console_manage').style.display = "block";
        admin.console_list();
     },

     title_manage : function() {
        admin.all_none();
        document.querySelector('#title_manage').style.display = "block";
        admin.title_list();
     },

     all_none : function() {
        document.querySelector('#console_manage').style.display = "none";
        document.querySelector('#title_manage').style.display = "none";
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
             admin.console_list();
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
          alert('수정되었습니다');
          document.querySelector('#console_update_cancel').click();
          admin.console_list();
      }).fail(function(error) {
          alert(JSON.stringify(error));
      });
  },

    console_delete : function() {
      if(confirm("상품을 삭제하시겠습니까?") == true) {
          var form = $('#console_update_form');
          var formData = new FormData(form[0]);
          var id = $('#admin_id').val();
          $.ajax({
              type: 'DELETE',
              url : '/admin/products/del/' + id,
          }).done(function() {
              alert('삭제되었습니다');
              document.querySelector('#console_update_cancel').click();
              admin.console_list();
          }).fail(function(error) {
              alert(JSON.stringify(error));
          });
      }
    },

     console_list : function() {

         $.ajax({
             type : 'GET',
             url : '/admin/consoles/consoleList',
             dataType : 'json',
         }).done(function(success) {
            $('#conList').children().remove();
            var consoleData = success;
            for(var i in consoleData) {
                 $('#conList').append('<div class=\"col-lg-4 col-sm-4\">'
                    + '<div class=\"single_product_item text-center\" onclick=\"admin.console_view(' + consoleData[i].id + ');\">'
                    +       '<img src=\"/display/'+consoleData[i].img_num+'\" alt=\"#\" class=\"img-fluid\">'
                    +       '<h4> 제조사 : ' + consoleData[i].manufact + ' </h4>'
                    +       '<h4> 제품명 : ' + consoleData[i].p_name + ' </h4>'
                    +       '<h5> 가격 : ' + admin.numberWithCommas(consoleData[i].p_price) + '원</h5>'
                    +       '<h5> 수량 : ' + consoleData[i].quantity + '개</h5>'
                    +       '</div>'
                    +  '</div>');
            }
         }).fail(function(error) {
             alert('게시글을 불러올수 없습니다');
         });
     },

     console_view : function(id) {
         $.ajax({
             type : 'GET',
             url : '/admin/consoles/view/' + id,
             dataType : 'json',
         }).done(function(success) {
            var consoleData = success;
            $('#up_manufact_nm').val(consoleData.manufact).prop("selected", true)
            document.querySelector('#up_manufact_nm_sel').textContent = consoleData.manufact;
            $('#_up_edition_nm').val(consoleData.p_name);
            $('#up_p_price').val(consoleData.p_price);
            $('#up_quantity').val(consoleData.quantity);
            $('#admin_id').val(consoleData.id);
            $("#console_update_modal").modal();
         }).fail(function(error) {
             alert('게시글을 불러올수 없습니다');
         });
     },


     title_insert : function() {

          var form = $('#title_insert_form');
          var formData = new FormData(form[0]);

          $.ajax({
              type: 'POST',
              url : '/admin/titles/save',
              data: formData,
              dataType: 'json',
              processData: false,
              contentType: false,
          }).done(function() {
              alert('게임타이틀이 등록되었습니다');
              document.querySelector('#title_insert_cancel').click();
              admin.title_list();
          }).fail(function(error) {
              alert(JSON.stringify(error));
          });
      },

      title_update : function() {

           var form = $('#title_update_form');
           var formData = new FormData(form[0]);
           var id = $('#admin_id').val();
           $.ajax({
               type: 'PUT',
               url : '/admin/titles/update/' + id,
               data: formData,
               dataType: 'json',
               processData: false,
               contentType: false,
           }).done(function() {
               alert('수정되었습니다');
               document.querySelector('#title_update_cancel').click();
               admin.title_list();
           }).fail(function(error) {
               alert(JSON.stringify(error));
           });
       },

     title_delete : function() {
       if(confirm("상품을 삭제하시겠습니까?") == true) {
           var form = $('#title_update_form');
           var formData = new FormData(form[0]);
           var id = $('#admin_id').val();
           $.ajax({
               type: 'DELETE',
               url : '/admin/products/del/' + id,
           }).done(function() {
               alert('삭제되었습니다');
               document.querySelector('#title_update_cancel').click();
               admin.title_list();
           }).fail(function(error) {
               alert(JSON.stringify(error));
           });
       }
     },

     title_list : function() {

      $.ajax({
          type : 'GET',
          url : '/admin/titles/titleList',
          dataType : 'json',
      }).done(function(success) {
         $('#titleList').children().remove();
         var consoleData = success;
         for(var i in consoleData) {
              $('#titleList').append('<div class=\"col-lg-4 col-sm-4\">'
                 + '<div class=\"single_product_item text-center\" onclick=\"admin.title_view(' + consoleData[i].id + ');\">'
                 +       '<img src=\"/display/'+consoleData[i].img_num+'\" alt=\"#\" class=\"img-fluid\">'
                 +       '<h4> 기종 : ' + consoleData[i].console + ' </h4>'
                 +       '<h4> 제작사 : ' + consoleData[i].manufact + ' </h4>'
                 +       '<h4> 제품명 : ' + consoleData[i].p_name + ' </h4>'
                 +       '<h5> 가격 : ' + admin.numberWithCommas(consoleData[i].p_price) + '원</h5>'
                 +       '<h5> 수량 : ' + consoleData[i].quantity + '개</h5>'
                 +       '</div>'
                 +  '</div>');
         }
      }).fail(function(error) {
          alert('게시글을 불러올수 없습니다');
      });
  },

  title_view : function(id) {
      $.ajax({
          type : 'GET',
          url : '/admin/titles/view/' + id,
          dataType : 'json',
      }).done(function(success) {
         var consoleData = success;
         $('#t_up_manufact_nm').val(consoleData.manufact);
         $('#t_up_console_nm').val(consoleData.console).prop("selected", true)
         document.querySelector('#up_console_nm_sel').textContent = consoleData.console;
         $('#t_up_edition_nm').val(consoleData.p_name);
         $('#t_up_p_price').val(consoleData.p_price);
         $('#t_up_quantity').val(consoleData.quantity);
         $('#admin_id').val(consoleData.id);
         $("#title_update_modal").modal();
      }).fail(function(error) {
          alert('게시글을 불러올수 없습니다');
      });
  },

     numberWithCommas : function (x) {
      	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
     },
}

admin.init();