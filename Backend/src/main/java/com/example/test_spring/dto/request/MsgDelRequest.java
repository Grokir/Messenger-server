package com.example.test_spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgDelRequest {
    private JSONArray msgIdArray;
}