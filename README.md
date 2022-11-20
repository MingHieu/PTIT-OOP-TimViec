# TimViec
Lưu ý : Code Android ở nhánh client, code Backend ở nhánh server. 
Nếu muốn chạy ứng dụng local phải clone project 2 lần. Một thư mục để chạy Android và thư mục còn lại để chạy Backend.

## Các bước cài đặt code Backend
Chuyển sang nhánh server để xem.

## Các bước cài đặt code Android
* Clone project và chuyển sang nhánh client
* Tải Android Studio : https://developer.android.com/studio
* Sau khi tải Android Studio, hãy cài đặt những thứ mà Android Studio yêu cầu ( 3 - 4 packages )
* Mở project vừa clone về bằng Android Studio và nhấn run ![Ảnh màn hình 2022-11-20 lúc 22 51 57](https://user-images.githubusercontent.com/77161145/202911920-06d8c5cf-819f-47a8-8fb1-f91e03fa45aa.png)
* Vào app/src/main/java/com/example/timviec/services/ApiService.java và chuyển baseURL về https://localhost:8080/api/

![Ảnh màn hình 2022-11-20 lúc 22 54 18](https://user-images.githubusercontent.com/77161145/202912043-ce585539-c8cf-4788-a7c6-c358cce15d67.png)
