package org.example.labmanagement.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.labmanagement.exception.Code;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    //vo类，返回操作业务码，和对应的信息
    private int code;       //操作出错，返回业务码
    private String message; // 操作出错，返回出错信息
    private Object data;   //操作成功，返回200业务码和具体信息，因为不知道什么类型，所以使用object

//    操作成功，返回空，无需每次创建相同的对象，因此创建单一对象，然后引用即可
    private static final ResultVO EMPTY = ResultVO.builder()
            .code(200)
            .build();
    public static ResultVO ok() {
        return EMPTY;
    }
    //操作成功，返回具体信息
    public static ResultVO succuss(Object data) {
        return ResultVO.builder()
                .code(200)
                .data(data)
                .build();
    }

    //操作失败，通用错误枚举
    public static ResultVO error(Code code) {
        return ResultVO.builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();
    }

    public static ResultVO error(int number, String message) {
        return ResultVO.builder()
                .code(number)
                .message(message)
                .build();
    }
}
