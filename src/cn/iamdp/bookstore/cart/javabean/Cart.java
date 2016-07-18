package cn.iamdp.bookstore.cart.javabean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	Map<String,CartItem> map=new LinkedHashMap<String,CartItem>(); 
	
	
	public  double getTotal(){//合计购物车=所有条目的小计之和
		BigDecimal  total=new BigDecimal("0");
		for (CartItem cartItem:map.values()){
			BigDecimal subtotal=new BigDecimal(""+cartItem.getSubtotal());
			total=total.add(subtotal);
		}
		return total.doubleValue();
	}
	
	public void add(CartItem cartitem){//添加条目
		//判断原来的车是否有存这本书的条目，有的话在里面把数量加一
		if(map.containsKey(cartitem.getBook().getBid())){
			CartItem cartitem1=map.get(cartitem.getBook().getBid());
			cartitem1.setCount(cartitem1.getCount()+cartitem.getCount());
		}else{
			map.put(cartitem.getBook().getBid(), cartitem);
		}
		
	}
	
	public void delete(String bid){//删除条目
		map.remove(bid);
	}
	
	public void clear(){//清空购物车
		map.clear();
	}
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
