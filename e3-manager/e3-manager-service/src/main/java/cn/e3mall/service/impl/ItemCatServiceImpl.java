package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.service.ItemCatService;
/**
 * 商品分类管理
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	public List<TreeNode> getItemCatlist(long parentId) {
//		1、根据parentid查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
//		设置查询条件
		criteria.andParentIdEqualTo(parentId);
//		执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
//		2、把List<TbItemCat>列表转换成List<TreeNode>
		List<TreeNode> treeNodes = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
		TreeNode node = new TreeNode();
		node.setId(tbItemCat.getId());
		node.setText(tbItemCat.getName());
//		state取决于当前节点是否是父节点，如果是父节点“closed”，如果不是父节点“open”
		node.setState(tbItemCat.getIsParent()?"closed":"open");
//		添加到列表
		treeNodes.add(node);
		}
//		3、返回List<TreeNode>
		return treeNodes;
	}

}
