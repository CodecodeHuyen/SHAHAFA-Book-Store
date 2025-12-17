
INSERT INTO categories (description,  name) VALUES
                                                   ( 'a','Tiểu thuyết'),
                                                   ( 'a', 'Truyện tranh'),
                                                   ( 'a', 'Kinh tế'),
                                                   ( 'a', 'Kỹ năng sống');



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

) VALUES
      (
          '0909123456',
          'Cửa hàng BookShop tại Hà Nội',
          'Hà Nội',
          'hanoi@bookshop.vn',
          '/images/logo-hn.png',
          'BookShop Hà Nội');


INSERT INTO publishers (name) VALUES
                                  (N'NXB Kim Đồng'),
                                  (N'NXB Trẻ'),
                                  (N'NXB Giáo Dục Việt Nam'),
                                  (N'NXB Lao Động'),
                                  (N'NXB Văn Học'),
                                  (N'NXB Tổng Hợp TP.HCM'),
                                  (N'NXB Phụ Nữ Việt Nam'),
                                  (N'NXB Thế Giới'),
                                  (N'NXB Đại Học Quốc Gia Hà Nội'),
                                  (N'NXB Hội Nhà Văn');


INSERT INTO books
(price, publication_date, quantity, weight, publisher_id,
 authors, isbn, title, url_image, description, status)
VALUES
    (130000.00,'2018-10-16',120,0.45,4,'James Clear','ISBN-AH-001','Atomic Habits',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Ffa3e48fc-bc81-4df8-9ade-0c7d1fa85ded-9780593189641.webp',
     'Thói quen nhỏ tạo nên thay đổi lớn.','ACTIVE'),

    (150000.00,'2016-01-05',60,0.5,4,'Cal Newport','ISBN-DW-001','Deep Work',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F4345ce32-6841-4c20-8bf4-68e8bb56bbf0-9781455563869.webp',
     'Nghệ thuật làm việc sâu trong thế giới xao nhãng.','ACTIVE'),

    (28000.00,'2002-03-01',150,0.15,1,'Lê Linh','ISBN-TDDV-001','Thần Đồng Đất Việt Tập 1',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F38b8dcca-ca79-44b6-b083-0ca4b74fe105-image_112979.webp',
     'Truyện tranh Việt Nam được yêu thích.','ACTIVE'),

    (28000.00,'2002-06-01',140,0.15,1,'Lê Linh','ISBN-TDDV-002','Thần Đồng Đất Việt Tập 2',
     'https://i.pinimg.com/736x/0d/f7/e9/0df7e959f8c2b4585e92b5c1cbff1e38.jpg',
     'Tiếp tục những câu chuyện hài hước của Tí, Sửu, Dần, Mẹo.','ACTIVE'),

    (115000.00,'2010-02-01',70,0.4,3,'Robin Sharma','ISBN-LD-001','Nhà Lãnh Đạo Không Chức Danh',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F854ca0f8-b7da-44ef-a173-88a0757a96b3-8934974179184.jpg',
     'Lãnh đạo bản thân và người khác mà không cần chức vụ.','ACTIVE'),

    (145000.00,'2013-12-01',80,0.4,4,'Koga Fumitake, Kishimi Ichiro','ISBN-DBG-001','Dám Bị Ghét',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F54213882-5da6-4214-bc04-9ec374731789-yho27ekd.webp',
     'Triết lý sống can đảm theo trường phái Adler.','ACTIVE'),

    (98000.00,'2016-09-10',65,0.3,5,'Tetsuko Kuroyanagi','ISBN-TTC-002',
     'Totto-chan: Cô Bé Bên Cửa Sổ (Bản Màu)',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F54a1c70d-bf06-4c6e-aa5f-be3e26637f03-8935235241848.webp',
     'Phiên bản minh họa màu đẹp mắt.','ACTIVE'),

    (32000.00,'1994-01-01',200,0.18,1,'Aoyama Gosho','ISBN-CONAN-001','Conan Tập 1',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F6a0fd1fb-f7f1-4e99-8035-3541c121d49f-image_182222_1.webp',
     'Thám tử lừng danh Conan tập 1.','ACTIVE'),

    (32000.00,'1994-05-01',190,0.18,1,'Aoyama Gosho','ISBN-CONAN-002','Conan Tập 2',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Fc418eb4f-4872-4437-b85e-ab5c59a12220-tham-tu-lung-danh-conan---tap-2---tb-2023.jpg',
     'Thám tử lừng danh Conan tập 2.','ACTIVE'),

    (100000.00,'2012-08-01',60,0.35,2,'Phạm Lữ Ân','ISBN-100N-001',
     'Nếu Biết Trăm Năm Là Hữu Hạn',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Fa2edc00e-e665-4a3b-ad60-d45ccf90f84d-8932000134749_1.webp',
     'Tản văn về tuổi trẻ, tình yêu và cuộc sống.','ACTIVE'),

    (90000.00,'2013-05-01',70,0.3,2,'Lưu Đồng','ISBN-TTX-001',
     'Tuổi Thanh Xuân Mơ Hồ',
     'https://i.pinimg.com/736x/0b/66/23/0b66238e06411a9d1c7bbff2a30bb94e.jpg',
     'Những câu chuyện buồn vui tuổi thanh xuân.','ACTIVE'),

    (125000.00,'1997-01-01',65,0.4,3,'Robin Sharma','ISBN-FERR-001',
     'Vị Tu Sĩ Bán Chiếc Ferrari',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Fb5e14cfe-bd09-48a9-8acf-b56c2b3d3cfd-image_195509_1_55840.webp',
     'Câu chuyện truyền cảm hứng về ý nghĩa cuộc sống.','ACTIVE'),

    (140000.00,'2014-03-01',50,0.45,4,'Cao Minh','ISBN-TTBD-001',
     'Thiên Tài Bên Trái, Kẻ Điên Bên Phải',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Fa50bbb48-9c69-444d-9df2-1dfc87a3ce79-b_a-thi_n-t_i-b_n-tr_i-k_-_i_n-b_n-ph_i.webp',
     'Những cuộc đối thoại với bệnh nhân tâm thần.','ACTIVE'),

    (95000.00,'2013-07-01',80,0.35,3,'Robin Sharma','ISBN-DN-001',
     'Đời Ngắn Đừng Ngủ Dài',
     'https://i.pinimg.com/736x/89/26/4d/89264d2be019994beb82bae841bacbb1.jpg',
     'Sống trọn vẹn từng ngày.','ACTIVE'),

    (89000.00,'2018-09-10',75,0.28,5,'Nguyễn Nhật Ánh','ISBN-HVTT-001',
     'Tôi Thấy Hoa Hồng Trên Cỏ Xanh (Tranh Truyện)',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Ffa98526f-0965-49a0-8961-1ccac945b1ce-nna-hvtcx.webp',
     'Phiên bản tranh truyện dễ thương.','ACTIVE'),

    (35000.00,'2019-07-04',160,0.2,1,'Tatsuya Endo','ISBN-SPY-001',
     'Spy X Family Tập 1',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Fea5a1c7d-65c6-418c-ab5c-ab99e6be7d43-spy---family---tap-7-_b_a-1__1.webp',
     'Gia đình điệp viên siêu lầy lội.','ACTIVE'),

    (35000.00,'2019-10-04',150,0.2,1,'Tatsuya Endo','ISBN-SPY-002',
     'Spy X Family Tập 2',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F2c17f08e-199e-4616-b1e3-1fce8f869d48-image-from-url.jpeg',
     'Tập 2 tiếp tục những câu chuyện hài hước.','ACTIVE'),

    (220000.00,'2011-10-25',40,0.7,4,'Daniel Kahneman','ISBN-TDNC-EN-001',
     'Thinking, Fast and Slow (Bản Tiếng Anh)',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Fd386dce6-66e1-4a42-a924-916f1255a9db-image-from-url.jpeg',
     'Bản tiếng Anh của Tư Duy Nhanh Và Chậm.','ACTIVE'),

    (45000.00,'2020-01-01',200,0.25,5,'Nhiều tác giả','ISBN-COLOR-001',
     'Sách Tô Màu Dành Cho Bé',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2F3d6e4154-24b4-4984-8049-f7c6260568ea-image-from-url.webp',
     'Sách tô màu giúp bé phát triển sáng tạo.','ACTIVE'),

    (250000.00,'2019-03-15',55,0.9,5,'Nhiều tác giả','ISBN-EN-001',
     'Combo Tự Học Tiếng Anh Giao Tiếp',
     'https://xo-cua-huyen-ne.s3.ap-southeast-1.amazonaws.com/SHAHAFA+Book+Store%2Fdc7ad235-e695-4b8b-8b42-2fb834128bd3-image-from-url.jpeg',
     'Combo sách tự học tiếng Anh giao tiếp cơ bản.','ACTIVE');


INSERT INTO book_category (book_id, category_id) VALUES
-- Atomic Habits, Deep Work: Kỹ năng sống + Kinh tế
(1, 3),
(2, 3),

-- Thần Đồng Đất Việt: Truyện tranh + Thiếu nhi
(3, 2),
(4, 2),

-- Nhà Lãnh Đạo Không Chức Danh: Kinh tế + Kỹ năng
(5, 3),

-- Dám Bị Ghét: Kỹ năng sống
(6, 4),

-- Totto-chan bản màu: Thiếu nhi
(7, 5),

-- Conan: Truyện tranh
(8, 2),
(9, 2),

-- Nếu Biết Trăm Năm Là Hữu Hạn, Tuổi Thanh Xuân Mơ Hồ: Tiểu thuyết
(10, 1),
(11, 1),

-- Vị Tu Sĩ Bán Chiếc Ferrari, Đời Ngắn Đừng Ngủ Dài: Kỹ năng sống
(12, 4),
(13, 4),

-- Thiên Tài Bên Trái Kẻ Điên Bên Phải: Kỹ năng / tâm lý
(14, 4),

-- Hoa Vàng Trên Cỏ Xanh tranh truyện: Thiếu nhi
(15, 5),

-- Spy X Family: Truyện tranh
(16, 2),
(17, 2),

-- Thinking Fast and Slow EN: Kinh tế + Kỹ năng
(18, 3),

-- Sách tô màu: Thiếu nhi
(19, 5),

-- Combo tiếng Anh: Kỹ năng sống
(20, 4);



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


INSERT INTO user (id, name, loyal_point, date_of_birth, gender, avatar_url)
VALUES
    (1, 'Nguyen Van A', 100, '1998-05-20', 'MALE', NULL),
    (2, 'Tran Thi B', 50, '2000-08-15', 'FEMALE', NULL);

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
(1, 1, 3, 35000),   -- Doraemon Tập 1
(1, 2, 2, 35000),
(1, 3, 1, 80000),

-- Order 2
(2, 1, 4, 35000),   -- Doraemon Tập 1 (bán rất chạy)
(2, 4, 2, 90000),

-- Order 3
(3, 3, 5, 80000),   -- Tuổi Trẻ Đáng Giá Bao Nhiêu
(3, 5, 3, 95000),
(3, 1, 2, 35000),

-- Order 4 (CANCELLED → ignore)
(4, 1, 10, 35000);

