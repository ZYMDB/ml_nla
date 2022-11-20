package com.example.nla.service;

import java.util.List;

public interface ExtractService {
    // 提取主语
    List<String> extractSubjects(List<String> contents);
    // 提取谓语
    List<String> extractPredicates(List<String> contents);
    // 提取宾语
    List<String> extractObjects(List<String> contents);
}
