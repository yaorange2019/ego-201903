package com.ego.item.web;

import com.ego.item.pojo.Category;
import com.ego.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> list(@RequestParam("pid") Long pid)
    {
        List<Category> result = categoryService.findListByPid(pid);
        if(result==null)
        {
            //405 404 403 401  200 201 500
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Category category)
    {
        categoryService.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam(value = "id",required = true) Long id,@RequestParam(value = "name",required = true)String name)
    {
        categoryService.updateNameById(id, name);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id")Long id)
    {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
