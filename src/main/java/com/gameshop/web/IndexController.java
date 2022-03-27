package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.CartProducts;
import com.gameshop.domain.cart.dto.CartProdListResDto;
import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.OrderDetail;
import com.gameshop.domain.order.dto.OrderConfirmListResponse;
import com.gameshop.domain.order.dto.OrderConfirmResponseDto;
import com.gameshop.domain.order.dto.OrderDetailResponseDto;
import com.gameshop.domain.products.dto.ProductsOrderResponseDto;
import com.gameshop.domain.products.dto.ProductsResponseDto;
import com.gameshop.domain.qnas.Qnas;
import com.gameshop.service.*;
import com.gameshop.domain.qnas.dto.CommentResponseDto;
import com.gameshop.domain.qnas.dto.QnasResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  IndexController - view를 리턴하는 컨트롤러
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final QnasService qnasService;
    private final ProductsService productsService;
    private final CartService cartService;
    private final OrderService orderService;
    private final CommentService commentService;

    /**
     * 메인페이지 로딩
     * @param model
     * @param user
     * @return view
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        return "index";
    }

    /**
     * 게시판 - 게시글 조회 페이지 로딩
     * @param model
     * @param user
     * @param p_num
     * @param search
     * @return
     */
    @GetMapping(value = {"/board/{p_num}", "/board/{p_num}/{search}"})
    public String board(Model model, @LoginUser SessionUser user, @PathVariable(required = false) Integer p_num
                , @PathVariable(required = false) String search){
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        if(p_num.equals(null)) {
            p_num = 1;
        }

        Page<Qnas> qnasList;
        if(search == null) {
            qnasList = qnasService.findAllPageDesc(p_num);
        } else {
            qnasList = qnasService.findByTitle(search, p_num);
        }

        boolean hasPrev = qnasList.hasPrevious();
        boolean hasNext = qnasList.hasNext();
        boolean hasLast = qnasList.isLast();
        boolean hasFirst = qnasList.isFirst();

        List<QnasResponseDto> dto = qnasList.getContent().stream()
                .map(QnasResponseDto::new)
                .collect(Collectors.toList());
        model.addAttribute("qnasList", dto);
        if(hasPrev) {
            model.addAttribute("prev", qnasList.getPageable().getPageNumber());
        }
        if(hasNext) {
            model.addAttribute("next", qnasList.getPageable().getPageNumber() +2);
        }
        if(!hasFirst) {
            model.addAttribute("first", 1);
        }
        if(!hasLast) {
            model.addAttribute("last", qnasList.getTotalPages());
        }
        model.addAttribute("search", search);

        return "board";
    }

    /**
     * 게시판 - 게시글 작성 페이지 로딩
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/board/write_board")
    public String write_board(Model model, @LoginUser SessionUser user){
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
        }
        return "write_board";
    }

    /**
     * 게시판 - 게시판 상세조회 페이지 로딩
     * @param id
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/board/view_board/{id}")
    public String view_board(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        List<CommentResponseDto> comment = new ArrayList<>();
        model.addAttribute("comment", comment = commentService.findAllQnas(id));
        QnasResponseDto dto = qnasService.findById(id);
        model.addAttribute("qnas", dto);
        return "view_board";
    }

    /**
     * 게시판 - 게시판 수정페이지 로딩
     * @param id
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/board/update_board/{id}")
    public String update_board(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        QnasResponseDto dto = qnasService.findById(id);
        model.addAttribute("qnas", dto);
        return "update_board";
    }

    /**
     * 상품 - 닌텐도 하드웨어 페이지 로딩
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/products/hardware-nintendo")
    public String hardware_nintendo(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "hardware-nintendo";
    }

    /**
     * 상품 - 플스5 하드웨어 페이지 로딩
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/products/hardware-ps5")
    public String hardware_ps5(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        return "hardware-ps5";
    }

    /**
     * 상품 - 닌텐도 소프트웨어 페이지 로딩
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/products/software-switch")
    public String software_switch(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        return "software-switch";
    }

    /**
     * 상품 - 플스5 소프트웨어 페이지 로딩
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/products/software-ps5")
    public String software_ps5(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        return "software-ps5";
    }

    /**
     * 상품 - 상세보기 페이지 로딩
     * @param id
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/products/view/{id}")
    public String view_products(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        ProductsResponseDto dto = productsService.findById(id);
        model.addAttribute("dto", dto);
        return "view_products";
    }

    /**
     * 장바구니 - 장바구니 페이지 로딩
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/cart")
    public String cart(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        List<CartProdListResDto> cartList = cartService.findAllCartUser(user);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    /**
     * 주문 - 주문 페이지 로딩 (장바구니에서 진행)
     * @param model
     * @param user
     * @param list_checked
     * @return
     */
    @GetMapping("/order")
    public String order(Model model, @LoginUser SessionUser user, @RequestParam(value = "list_checked")List<String> list_checked) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        int total_price = 0;
        List<CartProdListResDto> cartList = new ArrayList<>();

        Long ready_count = orderService.find_order_ready_cnt(user.getId());
        Order order;
        if(ready_count != 0L) {
            order = orderService.find_order_ready(user.getId());
            order.initOrderProducts();
        } else {
            order = orderService.order_ready(user.getId());
        }

        OrderDetail orderDetail;
        //주문상세 추가, 주문가격
        for(String list_id : list_checked) {
            Long cartProduct_id = Long.parseLong(list_id);
            CartProdListResDto cartProducts = cartService.CartProdFindById(cartProduct_id);
            cartList.add(cartProducts);
            CartProducts orderProduct = cartService.cartProdEntityFindById(cartProduct_id);
            orderProduct.setOrder(null);
            order.addOrderProducts(orderProduct);
        }

        for(CartProdListResDto cart : cartList) {
            total_price += cart.getOrder_price();
            order.setOrder_price(total_price);
        }
        model.addAttribute("orderList", cartList);

        return "order";
    }

    /**
     * 주문 - 주문 페이지 로딩 (상품상세보기에서 한가지의 상품주문)
     * @param model
     * @param user
     * @param id
     * @param quantity
     * @return
     */
    @GetMapping("/order/one/{id}/{quantity}")
    public String order(Model model, @LoginUser SessionUser user, @PathVariable Long id, @PathVariable int quantity) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        ProductsOrderResponseDto dto = productsService.findByIdOrder(id, quantity);
        model.addAttribute("orderList", dto);

        return "order";
    }

    /**
     * 주문확인 - 주문 리스트 페이지 로딩
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/order/confirm")
    public String orderConfirm(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        List<OrderConfirmListResponse> orderList = orderService.orderList(user.getId());
        model.addAttribute("orderList", orderList);

        return "order_confirm";
    }

    /**
     * 주문확인 - 주문리스트중 상세 주문내역 페이지 로딩
     * @param id
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/order/confirm/detail/{id}")
    public String orderConfirmDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        OrderConfirmResponseDto order_confirm = orderService.order_confirm(id);
        List<OrderDetailResponseDto> orderDetailList = orderService.orderDetailProductList(id);
        model.addAttribute("order_confirm", order_confirm);
        model.addAttribute("orderDetailList", orderDetailList);

        return "order_confirm_detail";
    }

    /**
     * 관리자페이지 - 관리자페이지 로딩(기능 미완성)
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/admin")
    public String admin_page(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo", userInfo);
            if (user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                return "admin";
            }
        }
        return null;
    }
}
