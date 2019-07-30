package com.ego.item.web;

import com.ego.common.pojo.PageResult;
import com.ego.item.pojo.Brand;
import com.ego.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> page(
            @RequestParam("pageNo")Integer pageNo,
            @RequestParam("pageSize")Integer pageSize,
            @RequestParam("sortBy")String sortBy,
            @RequestParam("descending") Boolean descending,
            @RequestParam("key")String key
    ){
        PageResult<Brand> result = brandService.page(pageNo,pageSize,sortBy,descending,key);

        if(result==null||result.getItems().size()==0)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
