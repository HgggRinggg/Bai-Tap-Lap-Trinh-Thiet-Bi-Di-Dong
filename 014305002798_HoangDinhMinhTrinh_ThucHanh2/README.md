# BÀI THỰC HÀNH 2

## Bài 1: Nhập Số

### 1. Mô tả bài tập

Tạo một màn hình đơn giản để nhập số. Dựa trên giá trị nhập:
- Nếu dữ liệu nhập vào là kiểu số, ứng dụng tạo ra một danh sách ô được đánh số từ 1 đến số đã nhập
- Nếu khác kiểu dữ liệu số (ví dụ: chữ cái, ký tự đặc biệt,...), ứng dụng hiển thị thông báo lỗi "Dữ liệu nhập vào không hợp lệ"
  
### 2. Mục tiêu
- Rèn luyện kỹ năng thiết kế giao diện người dùng (UI) đơn giản
- Xử lý input từ người dùng
- Tạo danh sách động

### 3. Kết quả

- Giao diện bao gồm:
  + Ô nhập liệu (TextInputEditText) cho phép người dùng thay đổi dữ liệu đầu vào.
  + Nút "Tạo" (Button) dùng để xử lý dữ liệu sau khi nhấn.
- Hiển thị danh sách các ô theo hàng dọc được đánh số thứ tự khi nhập vào một số n (1, 2, 3,...)
- Hiển thị thông báo lỗi khi nhập vào dữ liệu không phải số nguyên
  
### 4. Giải thích

   ```bash
   fun xuLyTao(view: View)
   ```
- `xuLyTao` là một hàm xử lý sự kiện, được gắn với nút "Tạo" trong file layout XML thông qua thuộc tính `android:onClick="xuLyTao"`.
- Khi người dùng nhấn vào nút này, Android sẽ tự động gọi hàm `xuLyTao`.
- Tham số `view` có kiểu `View`, đại diện cho chính thành phần giao diện vừa bị nhấn.


   ```bash
   baoLoi.visibility = View.GONE
   danhSach.removeAllViews()
   ```
- Ẩn thông báo lỗi nếu đang hiển thị và xóa toàn bộ ô cũ trong `LinearLayout` để tránh bị chồng lặp.

  
   ```bash
   val chuNhap = nhapSo.text.toString().trim()
   val soNhap = chuNhap.toIntOrNull()
   ```
- Lấy giá trị được nhập từ `TextInputEditText` và chuyển chuỗi thành số nguyên, nếu không hợp lệ sẽ trả về `null`.


   ```bash
   if (soNhap == null || soNhap <= 0)
   {
        baoLoi.visibility = View.VISIBLE
        return
   }
   ```
- Hiển thị báo lỗi nếu giá trị không hợp lệ


   ```bash
   for (i in 1..soNhap)
   {
        val oMoi = Button(this)
        oMoi.layoutParams = oMau.layoutParams
        oMoi.text = "$i"
        oMoi.setBackgroundResource(R.drawable.rex_box)
        oMoi.setTextColor(oMau.textColors)
        oMoi.typeface = oMau.typeface

        danhSach.addView(oMoi)
   }
   ```
- Nếu hợp lệ, duyệt từ 1 đến số được nhập vào và tạo thêm ô có kích thước, margin, màu chữ, font chữ giống nút mẫu. Đánh số tương ứng cho ô và đặt nền bằng `rex_box.xml`.
- Thêm ô mới tạo vào `LinearLayout`.

     
### Minh họa

<p align="center">
  <img src="https://github.com/user-attachments/assets/c5664a37-6a8f-4be5-b810-24f5479e94b6" alt="Nhapso" width="200"/>
  <img src="https://github.com/user-attachments/assets/4b9863c1-6669-4210-9319-82edc97fe74e" alt="Nhapso_2" width="200"/>
</p>

