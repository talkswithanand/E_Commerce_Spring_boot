package com.tyss.shopapp.util;

import java.util.List;

import com.tyss.shopapp.entity.OrderInfo;

public class OrderBooking {
		private OrderInfo orderInfo;
		private List<Integer> productIds;
		public OrderInfo getOrderInfo() {
			return orderInfo;
		}
		public void setOrderInfo(OrderInfo orderInfo) {
			this.orderInfo = orderInfo;
		}
		public List<Integer> getProductIds() {
			return productIds;
		}
		public void setProductIds(List<Integer> productIds) {
			this.productIds = productIds;
		}
}
