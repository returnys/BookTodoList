package com.green.booktodolist.todoList.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelCategoryDto {
    private String cate_name;
    private int itodo;
    private String title;
    private int bookmark;
    private String totalpage;
    private int del;
    private int finish;
    private String start;
    private String end;
    private String color;
    private String isbn;
}
