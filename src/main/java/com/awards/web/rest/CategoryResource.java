package com.awards.web.rest;

import com.awards.domain.Category;
import com.awards.repository.CategoryRepository;
import com.wordnik.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app")
@Api(value = "Category", description = "Search, create and delete categories")
public class CategoryResource {
    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @ApiOperation(value = "returns a list of categories")
    @ApiResponses( {@ApiResponse( code = 200, message = "Should always return Http Status OK" )} )
    @RequestMapping(value = "/rest/categories", method = RequestMethod.GET)
    public ResponseEntity<?> getCategories() {
        return new ResponseEntity<List<Category>>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "deletes one category using category-id")
    @ApiResponses( {@ApiResponse( code = 200, message = "Should always return Http Status OK" )} )
    @RequestMapping(value = "/rest/category/delete/{category-id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@ApiParam(name="category-id", value="The Id of the product to be deleted", required=true)
                                            @PathVariable("category-id") String categoryId) {
        log.info("deleting category with id: {}", categoryId);
        categoryRepository.delete(categoryId);
        return new ResponseEntity<String>("Category deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "saves a list of categories")
    @ApiResponses( {@ApiResponse( code = 201, message = "Should always return Http Status CREATED" )} )
    @RequestMapping(value = "/rest/category/create", method = RequestMethod.POST)
    public ResponseEntity<?> saveCategories(@ApiParam(name = "List of categories", value = "List of categories to be saved", required = true)
                                                @RequestBody(required = true) List<Category> categories) {
        log.info("Saving category {}", categories.toString());
        categoryRepository.save(categories);
        return new ResponseEntity<String>("Categories created", HttpStatus.CREATED);
    }

    @ApiOperation(value = "updates a list of categories")
    @ApiResponses( {@ApiResponse( code = 200, message = "Should always return Http Status OK" )} )
    @RequestMapping(value = "/rest/category/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateCategories(@ApiParam(name = "List of categories", value = "List of categories to be updated", required = true)
                                              @RequestBody(required = true) List<Category> categories) {
        log.info("Updating category {}", categories.toString());
        categoryRepository.save(categories);
        return new ResponseEntity<String>("Categories created", HttpStatus.OK);
    }

    @ApiOperation(value = "return one category using category-id")
    @ApiResponses( {@ApiResponse( code = 200, message = "Should always return Http Status OK" )} )
    @RequestMapping(value = "/rest/category/{category-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategoryById(@ApiParam(name="category-id", value="The Id of the product to be retrieved", required=true)
                                             @PathVariable("category-id") String categoryId) {
        return new ResponseEntity<Category>(categoryRepository.findOne(categoryId), HttpStatus.OK);
    }
}
