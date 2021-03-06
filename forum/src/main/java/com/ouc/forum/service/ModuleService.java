package com.ouc.forum.service;

import com.ouc.forum.DTO.ModuleDTO;
import com.ouc.forum.entity.Module;
import com.ouc.forum.entity.Tie;
import com.ouc.forum.repository.ModuleRepository;
import com.ouc.forum.repository.TieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author fxjy
 * @Date 2020/9/9 13:17
 * @Version 1.0
 */
@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private TieRepository tieRepository;

    /**
     * @Author Tan Mingyao
     * @Description
     * @LastModified 20:23 2020/9/10
     * @Param []
     * @Return java.util.List<com.ouc.forum.entity.Module>
     **/
    public List<Module> getAll() {
        return moduleRepository.findAll();
    }

    /**
     * @Author Tan Mingyao
     * @Description
     * @LastModified 20:23 2020/9/10
     * @Param [mid]
     * @Return com.ouc.forum.entity.Module
     **/
    public ModuleDTO get(Long mid) {
        Optional<Module> moduleOptional = moduleRepository.findById(mid);
        Module module = moduleOptional.orElse(null);
        long tieNum = tieRepository.tieCount(mid);
        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setId(module.getId());
        moduleDTO.setCover(module.getCover());
        moduleDTO.setName(module.getName());
        moduleDTO.setInfo(module.getInfo());
        moduleDTO.setTieNum(tieNum);
        return moduleDTO;
    }

    /**
     * @Author Tan Mingyao
     * @Description
     * @LastModified 20:27 2020/9/10
     * @Param [mid]
     * @Return java.util.List<com.ouc.forum.entity.Tie>
     **/
    public List<Tie> getTieByPage(Long mid, int pageNum) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(pageNum - 1, 6, sort); // （当前页， 每页记录数， 排序方式）
        Page<Tie> tieList = tieRepository.findByMid(mid, pageable);
        return tieList.getContent();
    }
}
