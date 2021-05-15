package com.se.pickple_api_server.v1.board.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardUpdateDto {

    @Data
    @AllArgsConstructor
    static public class Request {
        private String title;
        private String text;
    }
}
