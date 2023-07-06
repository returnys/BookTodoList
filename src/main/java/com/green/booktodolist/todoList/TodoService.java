package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {
    private final TodoMapper mapper;

    @Autowired
    public TodoService(TodoMapper mapper) {
        this.mapper = mapper;
    }

    public SelMainVo selMain(){

        int level = mapper.selUserLevel();
        int count = mapper.selUserCount();

        List<SelCategoryDto> selcategorylist = mapper.selcategorylist();
        return SelMainVo.builder().level(level).count(count).icategory(selcategorylist).build();

    }
    public int updel(UpdDel dto){
        return mapper.updel(dto);
    }
    public SelDetailDto selDetail(int itodo){
        SelDetailDto dto = mapper.selDetail(itodo);
        dto.setItodo(itodo);
        String finishYn = dto.getFinish();

        if (finishYn.equals("1")){
            dto.setFinish("완료");
        }else dto.setFinish("미완료");
        return dto;

    }

    public int UpdTodo(UpdTodoDto dto){

        int count = (int) (Math.random() * 10) + 1;
        SelDetailDto selTodo = mapper.selTodo(dto.getItodo());

        if (dto.getStart().equals("") ||dto.getEnd().equals("") || dto.getMemo().equals("") ){
            if (dto.getStart().equals("")){
                dto.setStart(selTodo.getStart());
            }if (dto.getMemo().equals("")) {
                dto.setMemo(selTodo.getMemo());
            }if (dto.getEnd().equals("")){
                dto.setEnd(selTodo.getEnd());
            }
        }

        if (dto.getFinish().equals("완료")){
            dto.setFinish("1");
        }else if (dto.getFinish().equals("미완료")){
            dto.setFinish("0");
        }
        if (selTodo.getFinish().equals("0") && dto.getFinish().equals("1")){
            mapper.updCount();
            mapper.updLevel();
        }
        return  mapper.updTodo(dto);
    }

    public int DelTodo(int itodo){
        DelTodoDto dto = new DelTodoDto();
        dto.setItodo(itodo);
        return mapper.DelTodo(dto);
    }
}
