package zxh.demo.tw.assignment.conference.domain.entity;

import zxh.demo.tw.assignment.conference.domain.vo.Length;

public class Talk {
    private final String name;
    private final Length length;

    public Talk(String name, Length length) {
        this.name = name;
        this.length = length;
    }
}
