INSERT IGNORE INTO bookshop.role (role_name, description)
VALUES
    ('CUSTOMER', 'Khách hàng sử dụng hệ thống'),
    ('MANAGER',  'Quản lý hệ thống'),
    ('ADMIN',    'Quản trị viên hệ thống');

INSERT INTO bookshop.categories (name, description) VALUES
                                                        (N'Văn học', N'Tiểu thuyết, truyện ngắn, thơ ca Việt Nam và thế giới'),
                                                        (N'Kinh tế', N'Sách về kinh doanh, tài chính, quản trị'),
                                                        (N'Tâm lý – Kỹ năng sống', N'Phát triển bản thân, tư duy, kỹ năng mềm'),
                                                        (N'Giáo dục', N'Sách học tập, phương pháp giảng dạy'),
                                                        (N'Thiếu nhi', N'Sách dành cho trẻ em'),
                                                        (N'Khoa học – Công nghệ', N'Sách về khoa học, CNTT, AI, lập trình'),
                                                        (N'Lịch sử', N'Sách lịch sử Việt Nam và thế giới'),
                                                        (N'Chính trị – Pháp luật', N'Chính trị, luật pháp, xã hội'),
                                                        (N'Văn hóa – Nghệ thuật', N'Âm nhạc, hội họa, điện ảnh'),
                                                        (N'Y học – Sức khỏe', N'Sức khỏe, dinh dưỡng, y học'),
                                                        (N'Truyện tranh – Manga', N'Truyện tranh Nhật Bản, Việt Nam'),
                                                        (N'Tôn giáo – Tâm linh', N'Triết lý sống, tôn giáo'),
                                                        (N'Ngoại ngữ', N'Sách học tiếng Anh, Nhật, Hàn, Trung'),
                                                        (N'Kỹ thuật', N'Sách kỹ thuật, cơ khí, điện – điện tử'),
                                                        (N'Nông nghiệp', N'Sách trồng trọt, chăn nuôi'),
                                                        (N'Du lịch', N'Cẩm nang du lịch trong và ngoài nước'),
                                                        (N'Ẩm thực', N'Sách nấu ăn, văn hóa ẩm thực'),
                                                        (N'Phong thủy', N'Phong thủy, tử vi, nhân tướng học'),
                                                        (N'Thể thao', N'Sách về thể thao, rèn luyện thể chất'),
                                                        (N'Truyện trinh thám', N'Trinh thám, hình sự, phá án');


INSERT INTO shop (
    hotline,
    description,
    address,
    email,
    logo_url,
    name
) VALUES ('0909123456',
          'Cửa hàng BookShop tại Hà Nội',
          'Hà Nội',
          'hanoi@bookshop.vn',
          '/images/logo-hn.png',
          'BookShop Hà Nội');



INSERT INTO books (
    id, title, price, isbn, authors, quantity,
    url_image, weight, description,
    publication_date, status, publisher_id, shop_id
) VALUES
      (21, 'Atomic Habits', 135000, 'ISBN-AH-001', 'James Clear', 80,
       'https://i.pinimg.com/736x/f0/c0/d5/f0c0d51e4e7d58f5b8b12ed78fe63c19.jpg', 0.45,
       'Thói quen nhỏ tạo nên thay đổi lớn.', '2018-10-16', 'ACTIVE', 4, 1),

      (22, 'Deep Work', 150000, 'ISBN-DW-001', 'Cal Newport', 60,
       'https://i.pinimg.com/736x/92/7c/88/927c887a74c1fd68f86c0f88f69d6b0a.jpg', 0.5,
       'Nghệ thuật làm việc sâu trong thế giới xao nhãng.', '2016-01-05', 'ACTIVE', 4, 1),

      (23, 'Thần Đồng Đất Việt Tập 1', 28000, 'ISBN-TDDV-001', 'Lê Linh', 150,
       'https://i.pinimg.com/736x/69/5a/0a/695a0a2f7cc0f38a9e3e9aa9bdfec66f.jpg', 0.15,
       'Truyện tranh Việt Nam được yêu thích.', '2002-03-01', 'ACTIVE', 1, 2),

      (24, 'Thần Đồng Đất Việt Tập 2', 28000, 'ISBN-TDDV-002', 'Lê Linh', 140,
       'https://i.pinimg.com/736x/0d/f7/e9/0df7e959f8c2b4585e92b5c1cbff1e38.jpg', 0.15,
       'Tiếp tục những câu chuyện hài hước của Tí, Sửu, Dần, Mẹo.', '2002-06-01', 'ACTIVE', 1, 2),

      (25, 'Nhà Lãnh Đạo Không Chức Danh', 115000, 'ISBN-LD-001', 'Robin Sharma', 70,
       'https://i.pinimg.com/736x/97/1b/7c/971b7c7e1e72f9279b5149bcb932d1b7.jpg', 0.4,
       'Lãnh đạo bản thân và người khác mà không cần chức vụ.', '2010-02-01', 'ACTIVE', 3, 1),

      (26, 'Dám Bị Ghét', 145000, 'ISBN-DBG-001', 'Koga Fumitake, Kishimi Ichiro', 80,
       'https://i.pinimg.com/736x/91/48/08/914808e0f866a61f0b6f53bfe3d4e44f.jpg', 0.4,
       'Triết lý sống can đảm theo trường phái Adler.', '2013-12-01', 'ACTIVE', 4, 1),

      (27, 'Totto-chan: Cô Bé Bên Cửa Sổ (Bản Màu)', 98000, 'ISBN-TTC-002', 'Tetsuko Kuroyanagi', 65,
       'https://i.pinimg.com/736x/52/1e/4f/521e4f8ff85fa96092d0f14e861a8f1e.jpg', 0.3,
       'Phiên bản minh họa màu đẹp mắt.', '2016-09-10', 'ACTIVE', 5, 2),

      (28, 'Conan Tập 1', 32000, 'ISBN-CONAN-001', 'Aoyama Gosho', 200,
       'https://i.pinimg.com/736x/08/f6/e3/08f6e3159f7943d4c0dd3ceb8c89c1d5.jpg', 0.18,
       'Thám tử lừng danh Conan tập 1.', '1994-01-01', 'ACTIVE', 1, 1),

      (29, 'Conan Tập 2', 32000, 'ISBN-CONAN-002', 'Aoyama Gosho', 190,
       'https://i.pinimg.com/736x/f0/5b/25/f05b257e7249d995772d4c2c563f4302.jpg', 0.18,
       'Thám tử lừng danh Conan tập 2.', '1994-05-01', 'ACTIVE', 1, 1),

      (30, 'Nếu Biết Trăm Năm Là Hữu Hạn', 100000, 'ISBN-100N-001', 'Phạm Lữ Ân', 60,
       'https://i.pinimg.com/736x/6f/7a/f6/6f7af6f2ab4ef64ea559fe934b1204aa.jpg', 0.35,
       'Tản văn về tuổi trẻ, tình yêu và cuộc sống.', '2012-08-01', 'ACTIVE', 2, 2),

      (31, 'Tuổi Thanh Xuân Mơ Hồ', 90000, 'ISBN-TTX-001', 'Lưu Đồng', 70,
       'https://i.pinimg.com/736x/0b/66/23/0b66238e06411a9d1c7bbff2a30bb94e.jpg', 0.3,
       'Những câu chuyện buồn vui tuổi thanh xuân.', '2013-05-01', 'ACTIVE', 2, 2),

      (32, 'Vị Tu Sĩ Bán Chiếc Ferrari', 125000, 'ISBN-FERR-001', 'Robin Sharma', 65,
       'https://i.pinimg.com/736x/c2/df/3b/c2df3be14c45f6915a51016a2160c081.jpg', 0.4,
       'Câu chuyện truyền cảm hứng về ý nghĩa cuộc sống.', '1997-01-01', 'ACTIVE', 3, 1),

      (33, 'Thiên Tài Bên Trái, Kẻ Điên Bên Phải', 140000, 'ISBN-TTBD-001', 'Cao Minh', 50,
       'https://i.pinimg.com/736x/ea/77/0d/ea770d60563a88c897fa45af0de1a4e2.jpg', 0.45,
       'Những cuộc đối thoại với bệnh nhân tâm thần.', '2014-03-01', 'ACTIVE', 4, 1),

      (34, 'Đời Ngắn Đừng Ngủ Dài', 95000, 'ISBN-DN-001', 'Robin Sharma', 80,
       'https://i.pinimg.com/736x/89/26/4d/89264d2be019994beb82bae841bacbb1.jpg', 0.35,
       'Sống trọn vẹn từng ngày.', '2013-07-01', 'ACTIVE', 3, 1),

      (35, 'Tôi Thấy Hoa Hồng Trên Cỏ Xanh (Tranh Truyện)', 89000, 'ISBN-HVTT-001', 'Nguyễn Nhật Ánh', 75,
       'https://i.pinimg.com/736x/d0/05/95/d00595a95f3b1c6e6235db6cac0c8a63.jpg', 0.28,
       'Phiên bản tranh truyện dễ thương.', '2018-09-10', 'ACTIVE', 5, 2),

      (36, 'Spy X Family Tập 1', 35000, 'ISBN-SPY-001', 'Tatsuya Endo', 160,
       'https://i.pinimg.com/736x/21/5e/5a/215e5a67ef177087d0af9f9ad788b705.jpg', 0.2,
       'Gia đình điệp viên siêu lầy lội.', '2019-07-04', 'ACTIVE', 1, 1),

      (37, 'Spy X Family Tập 2', 35000, 'ISBN-SPY-002', 'Tatsuya Endo', 150,
       'https://i.pinimg.com/736x/ea/56/65/ea56655ea291b8a8e4ea9a8f05bd59e4.jpg', 0.2,
       'Tập 2 tiếp tục những câu chuyện hài hước.', '2019-10-04', 'ACTIVE', 1, 1),

      (38, 'Thinking, Fast and Slow (Bản Tiếng Anh)', 220000, 'ISBN-TDNC-EN-001', 'Daniel Kahneman', 40,
       'https://i.pinimg.com/736x/f6/53/46/f653460ff9cf539441053568833f87b8.jpg', 0.7,
       'Bản tiếng Anh của Tư Duy Nhanh Và Chậm.', '2011-10-25', 'ACTIVE', 4, 1),

      (39, 'Sách Tô Màu Dành Cho Bé', 45000, 'ISBN-COLOR-001', 'Nhiều tác giả', 200,
       'https://i.pinimg.com/736x/95/44/3e/95443edef5c9a6e0f7a59458cdc8e90d.jpg', 0.25,
       'Sách tô màu giúp bé phát triển sáng tạo.', '2020-01-01', 'ACTIVE', 5, 2),

      (40, 'Combo Tự Học Tiếng Anh Giao Tiếp', 250000, 'ISBN-EN-001', 'Nhiều tác giả', 55,
       'https://i.pinimg.com/736x/9e/07/9e/9e079e7e3e8ebefb5632c2c960f119f3.jpg', 0.9,
       'Combo sách tự học tiếng Anh giao tiếp cơ bản.', '2019-03-15', 'ACTIVE', 5, 1);

INSERT INTO book_category (book_id, category_id) VALUES
-- Atomic Habits, Deep Work: Kỹ năng sống + Kinh tế
(21, 3), (21, 4),
(22, 3), (22, 4),

-- Thần Đồng Đất Việt: Truyện tranh + Thiếu nhi
(23, 2), (23, 5),
(24, 2), (24, 5),

-- Nhà Lãnh Đạo Không Chức Danh: Kinh tế + Kỹ năng
(25, 3), (25, 4),

-- Dám Bị Ghét: Kỹ năng sống
(26, 4),

-- Totto-chan bản màu: Thiếu nhi
(27, 5),

-- Conan: Truyện tranh
(28, 2),
(29, 2),

-- Nếu Biết Trăm Năm Là Hữu Hạn, Tuổi Thanh Xuân Mơ Hồ: Tiểu thuyết
(30, 1),
(31, 1),

-- Vị Tu Sĩ Bán Chiếc Ferrari, Đời Ngắn Đừng Ngủ Dài: Kỹ năng sống
(32, 4),
(34, 4),

-- Thiên Tài Bên Trái Kẻ Điên Bên Phải: Kỹ năng / tâm lý
(33, 4),

-- Hoa Vàng Trên Cỏ Xanh tranh truyện: Thiếu nhi
(35, 5),

-- Spy X Family: Truyện tranh
(36, 2),
(37, 2),

-- Thinking Fast and Slow EN: Kinh tế + Kỹ năng
(38, 3), (38, 4),

-- Sách tô màu: Thiếu nhi
(39, 5),

-- Combo tiếng Anh: Kỹ năng sống
(40, 4);



INSERT INTO user (name, loyal_point, date_of_birth, gender, avatar_url)
VALUES
    ('Nguyen Van A', 100, '1998-05-20', 'MALE', NULL),
    ('Tran Thi B', 50, '2000-08-15', 'FEMALE', NULL);

INSERT INTO orders (
    id, total_price, discount, shipping_fee,
    pickup_addr, delivery_addr,
    order_status, code, user_id
)
VALUES
    (1, 500000, 0, 30000, 'Kho HN', 'Ha Noi', 'COMPLETE', 'SHAHAFA000000001', 1),
    (2, 420000, 0, 30000, 'Kho HN', 'Ha Noi', 'COMPLETE', 'SHAHAFA000000002', 1),
    (3, 800000, 50000, 30000, 'Kho HCM', 'HCM', 'COMPLETE', 'SHAHAFA000000003', 2),
    (4, 300000, 0, 30000, 'Kho HCM', 'HCM', 'CANCELLED', 'SHAHAFA000000004', 2);

INSERT INTO order_items (order_id, book_id, quantity, unit_price)
VALUES
-- Order 1
(1, 21, 3, 35000),   -- Doraemon Tập 1
(1, 22, 2, 35000),
(1, 23, 1, 80000),

-- Order 2
(2, 21, 4, 35000),   -- Doraemon Tập 1 (bán rất chạy)
(2, 24, 2, 90000),

-- Order 3
(3, 23, 5, 80000),   -- Tuổi Trẻ Đáng Giá Bao Nhiêu
(3, 25, 3, 95000),
(3, 21, 2, 35000),

-- Order 4 (CANCELLED → ignore)
(4, 21, 10, 35000);
