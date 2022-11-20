# TimViec
Cách cài đặt server TimViec local

  1. Cài đặt VSCode, Docker.
  2. Cài đặt Java Package Extension, Spring Initializr Java Support trong VSCode
  3. Clone project và chuyển sang nhánh server
  4. Truy cập vào file resources/application.properties thay đổi "spring.datasource.url" thành "jdbc:postgresql://localhost:5432/timviec", "spring.datasource.username" thành "postgres" và "spring.datasource.password" thành "postgrespw"
  5. Khởi tạo database bằng command "docker compose up"
  6. Sau khi khởi tạo database, chạy server bằng command "./mvnw spring-boot:run"
  7. Server localhost chạy trên url "localhost:8080"
