package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//建立 Server 與 Client 傳遞資料上的統一結構與標準含錯誤
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
	
	private String message;
	T data;
	
	//成功
	public static <T> ApiResponse<T> success(String message,T data){
		return new ApiResponse<T>(message,data);
	}
	//失敗
	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<T>( message, null);
	}
	
}
