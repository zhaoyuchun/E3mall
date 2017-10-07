package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public TbItem getItemById(long itemId) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

	@Override
	public DataGridResult getItemList(int page, int rows) {
		//设置分页参数
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 3、从查询结果中取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		//创建一个DataGridResult对象
		DataGridResult dataGridResult = new DataGridResult();
		//把查询结果封装到DataGridResult对象中
		dataGridResult.setRows(list);
		dataGridResult.setTotal(total);
		//返回DataGridResult对象
		return dataGridResult;
	}
	/**
	 * 商品保存
	 */
	@Override
	public E3Result addItem(TbItem item, String desc) {
//		1、生成商品id
		long id = IDUtils.genItemId();
//		2、补全item对象的属性
		item.setId(id);
		//1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
//		3、向商品表插入数据
		itemMapper.insert(item);
//		4、创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
//		5、补全属性
		itemDesc.setCreated(new Date());
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(id);
		itemDesc.setUpdated(new Date());
//		6、向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
//		7、返回成功
		return E3Result.ok();
	}

}
