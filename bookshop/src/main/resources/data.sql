INSERT IGNORE INTO bookshop.role (role_name, description)
VALUES
    ('CUSTOMER', 'Khách hàng sử dụng hệ thống'),
    ('MANAGER',  'Quản lý hệ thống'),
    ('ADMIN',    'Quản trị viên hệ thống');

INSERT INTO categories (id, name) VALUES
                                      (1, 'Tiểu thuyết'),
                                      (2, 'Truyện tranh'),
                                      (3, 'Kinh tế'),
                                      (4, 'Kỹ năng sống'),
                                      (5, 'Thiếu nhi');

INSERT INTO publishers (id, name) VALUES
                                      (1, 'NXB Kim Đồng'),
                                      (2, 'NXB Trẻ'),
                                      (3, 'NXB Tổng Hợp'),
                                      (4, 'NXB Lao Động'),
                                      (5, 'NXB Giáo Dục');

INSERT INTO shop (address, description, logo_url, name) VALUES
                                                            ('Hà Nội', 'Cửa hàng BookShop tại Hà Nội', '/images/logo-hn.png', 'BookShop Hà Nội'),
                                                            ('TP. Hồ Chí Minh', 'Cửa hàng BookShop tại Sài Gòn', '/images/logo-sg.png', 'BookShop Sài Gòn');



INSERT INTO books (
    id, title, price, isbn, authors, quantity,
    url_image, weight, sku, description,
    publication_date, status, publisher_id, shop_id
) VALUES
      (1, 'Doraemon Tập 1', 35000, 'ISBN-DR-001', 'Fujiko F. Fujio', 100,
       'https://i.pinimg.com/736x/18/47/65/1847656cfefa0129b468c08ba4d7f6c3.jpg', 0.20, 'SKU-DR-001', 'Truyện tranh Doraemon tập 1',
       '2020-01-10', 'ACTIVE', 1, 1),

      (2, 'Doraemon Tập 2', 35000, 'ISBN-DR-002', 'Fujiko F. Fujio', 80,
       'https://i.pinimg.com/1200x/a1/f8/87/a1f88733921c820db477d054fe96afbb.jpg', 0.20, 'SKU-DR-002', 'Truyện tranh Doraemon tập 2',
       '2020-02-10', 'ACTIVE', 1, 1),

      (3, 'Tuổi Trẻ Đáng Giá Bao Nhiêu', 80000, 'ISBN-TT-001', 'Rosie Nguyễn', 50,
       'https://i.pinimg.com/736x/77/d6/57/77d65719efb07258fee3e620ad7a2d83.jpg', 0.30, 'SKU-TT-001', 'Sách kỹ năng sống cho giới trẻ',
       '2016-06-01', 'ACTIVE', 2, 1),

      (4, 'Đắc Nhân Tâm', 90000, 'ISBN-DNT-001', 'Dale Carnegie', 120,
       'https://i.pinimg.com/1200x/b0/f8/b3/b0f8b33c2b57904c6701701f1f821e0d.jpg', 0.35, 'SKU-DNT-001', 'Sách kỹ năng sống kinh điển',
       '2010-01-01', 'ACTIVE', 3, 1),

      (5, 'Nhà Giả Kim', 95000, 'ISBN-NGK-001', 'Paulo Coelho', 70,
       'https://i.pinimg.com/1200x/1e/43/32/1e43322d5a9cc5ceae41fff28e09e879.jpg', 0.30, 'SKU-NGK-001', 'Tiểu thuyết truyền cảm hứng',
       '2012-05-01', 'ACTIVE', 2, 2),

      (6, 'Totto-chan Bên Cửa Sổ', 75000, 'ISBN-TTC-001', 'Tetsuko Kuroyanagi', 60,
       'https://i.pinimg.com/736x/80/70/4c/80704c91ca89ca958e4ed0ae777a57df.jpg', 0.25, 'SKU-TTC-001', 'Sách thiếu nhi nổi tiếng',
       '2015-03-10', 'ACTIVE', 5, 2),

      (7, 'Cha Giàu Cha Nghèo', 120000, 'ISBN-CG-001', 'Robert Kiyosaki', 90,
       'https://i.pinimg.com/736x/c1/c9/8d/c1c98d023a2ecd0d51c58d9a3fbae735.jpg', 0.40, 'SKU-CG-001', 'Sách tài chính kinh tế cá nhân',
       '2014-08-20', 'ACTIVE', 3, 1),

      (8, '7 Thói Quen Hiệu Quả', 110000, 'ISBN-7H-001', 'Stephen R. Covey', 80,
       'https://i.pinimg.com/736x/16/10/3c/16103c2f0e84226165780400e20823ea.jpg', 0.40, 'SKU-7H-001', 'Sách kỹ năng sống - phát triển bản thân',
       '2011-11-11', 'ACTIVE', 4, 2),

      (9, 'Sherlock Holmes Toàn Tập (Tập 1)', 150000, 'ISBN-SH-001', 'Arthur Conan Doyle', 40,
       'https://i.pinimg.com/736x/30/4d/62/304d629cdcaf785b6b3028abe1d68018.jpg', 0.5, 'SKU-SH-001',
       'Tập 1 tuyển tập truyện trinh thám Sherlock Holmes.', '2018-01-10', 'ACTIVE', 3, 1),

      (10, 'Sherlock Holmes Toàn Tập (Tập 2)', 155000, 'ISBN-SH-002', 'Arthur Conan Doyle', 35,
       'https://i.pinimg.com/736x/85/d8/e2/85d8e281c5508ef7fc53fea8b2c6f9c8.jpg', 0.5, 'SKU-SH-002',
       'Tập 2 tuyển tập truyện trinh thám Sherlock Holmes.', '2018-06-15', 'ACTIVE', 3, 1),

      (11, 'One Piece Tập 1', 30000, 'ISBN-OP-001', 'Eiichiro Oda', 150,
       'https://i.pinimg.com/1200x/dc/39/63/dc396377c73f9461db831b1eedaa6d96.jpg', 0.18, 'SKU-OP-001',
       'Manga One Piece tập 1.', '2010-03-01', 'ACTIVE', 1, 1),

      (12, 'One Piece Tập 2', 30000, 'ISBN-OP-002', 'Eiichiro Oda', 140,
       'https://i.pinimg.com/1200x/80/ce/eb/80ceeb56b0309ebd9e86d80b7f4b86f2.jpg', 0.18, 'SKU-OP-002',
       'Manga One Piece tập 2.', '2010-05-10', 'ACTIVE', 1, 1),

      (13, 'Harry Potter và Hòn Đá Phù Thủy', 120000, 'ISBN-HP-001', 'J.K. Rowling', 90,
       'https://i.pinimg.com/736x/e5/87/98/e587981fb9dbc29ccb644c74197b5490.jpg', 0.6, 'SKU-HP-001',
       'Tập 1 trong series Harry Potter nổi tiếng.', '2000-09-01', 'ACTIVE', 2, 2),

      (14, 'Harry Potter và Phòng Chứa Bí Mật', 125000, 'ISBN-HP-002', 'J.K. Rowling', 85,
       'https://i.pinimg.com/736x/b3/79/9c/b3799c20a8b16273db1e442d607927e7.jpg', 0.6, 'SKU-HP-002',
       'Tập 2 trong series Harry Potter.', '2001-09-01', 'ACTIVE', 2, 2),

      (15, 'Tư Duy Nhanh Và Chậm', 180000, 'ISBN-TDNC-001', 'Daniel Kahneman', 50,
       'https://i.pinimg.com/1200x/9c/4f/10/9c4f106f062d69f59f7c426a2c203a18.jpg', 0.7, 'SKU-TDNC-001',
       'Cuốn sách kinh điển về tâm lý học hành vi.', '2015-04-20', 'ACTIVE', 4, 1),

      (16, 'Nghĩ Giàu Làm Giàu', 130000, 'ISBN-NGLG-001', 'Napoleon Hill', 70,
       'https://cdn0.fahasa.com/media/catalog/product/n/g/nghi_giau_lam_giau.jpg', 0.45, 'SKU-NGLG-001',
       'Sách kinh điển về tư duy thành công và làm giàu.', '2012-01-05', 'ACTIVE', 3, 1),

      (17, 'Mắt Biếc', 95000, 'ISBN-MB-001', 'Nguyễn Nhật Ánh', 100,
       'https://cdn0.fahasa.com/media/catalog/product/m/a/mat_biec.jpg', 0.3, 'SKU-MB-001',
       'Tiểu thuyết nổi tiếng của Nguyễn Nhật Ánh.', '1990-07-01', 'ACTIVE', 2, 2),

      (18, 'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 98000, 'ISBN-HVTCX-001', 'Nguyễn Nhật Ánh', 90,
       'https://cdn0.fahasa.com/media/catalog/product/h/o/hoa_vang_tren_co_xanh.jpg', 0.3, 'SKU-HVTCX-001',
       'Tác phẩm thiếu nhi giàu cảm xúc.', '2010-08-12', 'ACTIVE', 2, 2),

      (19, 'Sapiens: Lược Sử Loài Người', 200000, 'ISBN-SP-001', 'Yuval Noah Harari', 60,
       'https://cdn0.fahasa.com/media/catalog/product/s/a/sapiens.jpg', 0.8, 'SKU-SP-001',
       'Cuốn sách nổi tiếng về lịch sử loài người.', '2014-02-01', 'ACTIVE', 4, 1),

      (20, 'Homo Deus: Lược Sử Tương Lai', 210000, 'ISBN-HD-001', 'Yuval Noah Harari', 55,
       'https://cdn0.fahasa.com/media/catalog/product/h/o/homo_deus.jpg', 0.8, 'SKU-HD-001',
       'Tác phẩm tiếp nối Sapiens về tương lai loài người.', '2016-05-01', 'ACTIVE', 4, 1);


INSERT INTO books (
    id, title, price, isbn, authors, quantity,
    url_image, weight, sku, description,
    publication_date, status, publisher_id, shop_id
) VALUES
      (21, 'Atomic Habits', 135000, 'ISBN-AH-001', 'James Clear', 80,
       'https://i.pinimg.com/736x/f0/c0/d5/f0c0d51e4e7d58f5b8b12ed78fe63c19.jpg', 0.45, 'SKU-AH-001',
       'Thói quen nhỏ tạo nên thay đổi lớn.', '2018-10-16', 'ACTIVE', 4, 1),

      (22, 'Deep Work', 150000, 'ISBN-DW-001', 'Cal Newport', 60,
       'https://i.pinimg.com/736x/92/7c/88/927c887a74c1fd68f86c0f88f69d6b0a.jpg', 0.5, 'SKU-DW-001',
       'Nghệ thuật làm việc sâu trong thế giới xao nhãng.', '2016-01-05', 'ACTIVE', 4, 1),

      (23, 'Thần Đồng Đất Việt Tập 1', 28000, 'ISBN-TDDV-001', 'Lê Linh', 150,
       'https://i.pinimg.com/736x/69/5a/0a/695a0a2f7cc0f38a9e3e9aa9bdfec66f.jpg', 0.15, 'SKU-TDDV-001',
       'Truyện tranh Việt Nam được yêu thích.', '2002-03-01', 'ACTIVE', 1, 2),

      (24, 'Thần Đồng Đất Việt Tập 2', 28000, 'ISBN-TDDV-002', 'Lê Linh', 140,
       'https://i.pinimg.com/736x/0d/f7/e9/0df7e959f8c2b4585e92b5c1cbff1e38.jpg', 0.15, 'SKU-TDDV-002',
       'Tiếp tục những câu chuyện hài hước của Tí, Sửu, Dần, Mẹo.', '2002-06-01', 'ACTIVE', 1, 2),

      (25, 'Nhà Lãnh Đạo Không Chức Danh', 115000, 'ISBN-LD-001', 'Robin Sharma', 70,
       'https://i.pinimg.com/736x/97/1b/7c/971b7c7e1e72f9279b5149bcb932d1b7.jpg', 0.4, 'SKU-LD-001',
       'Lãnh đạo bản thân và người khác mà không cần chức vụ.', '2010-02-01', 'ACTIVE', 3, 1),

      (26, 'Dám Bị Ghét', 145000, 'ISBN-DBG-001', 'Koga Fumitake, Kishimi Ichiro', 80,
       'https://i.pinimg.com/736x/91/48/08/914808e0f866a61f0b6f53bfe3d4e44f.jpg', 0.4, 'SKU-DBG-001',
       'Triết lý sống can đảm theo trường phái Adler.', '2013-12-01', 'ACTIVE', 4, 1),

      (27, 'Totto-chan: Cô Bé Bên Cửa Sổ (Bản Màu)', 98000, 'ISBN-TTC-002', 'Tetsuko Kuroyanagi', 65,
       'https://i.pinimg.com/736x/52/1e/4f/521e4f8ff85fa96092d0f14e861a8f1e.jpg', 0.3, 'SKU-TTC-002',
       'Phiên bản minh họa màu đẹp mắt.', '2016-09-10', 'ACTIVE', 5, 2),

      (28, 'Conan Tập 1', 32000, 'ISBN-CONAN-001', 'Aoyama Gosho', 200,
       'https://i.pinimg.com/736x/08/f6/e3/08f6e3159f7943d4c0dd3ceb8c89c1d5.jpg', 0.18, 'SKU-CONAN-001',
       'Thám tử lừng danh Conan tập 1.', '1994-01-01', 'ACTIVE', 1, 1),

      (29, 'Conan Tập 2', 32000, 'ISBN-CONAN-002', 'Aoyama Gosho', 190,
       'https://i.pinimg.com/736x/f0/5b/25/f05b257e7249d995772d4c2c563f4302.jpg', 0.18, 'SKU-CONAN-002',
       'Thám tử lừng danh Conan tập 2.', '1994-05-01', 'ACTIVE', 1, 1),

      (30, 'Nếu Biết Trăm Năm Là Hữu Hạn', 100000, 'ISBN-100N-001', 'Phạm Lữ Ân', 60,
       'https://i.pinimg.com/736x/6f/7a/f6/6f7af6f2ab4ef64ea559fe934b1204aa.jpg', 0.35, 'SKU-100N-001',
       'Tản văn về tuổi trẻ, tình yêu và cuộc sống.', '2012-08-01', 'ACTIVE', 2, 2),

      (31, 'Tuổi Thanh Xuân Mơ Hồ', 90000, 'ISBN-TTX-001', 'Lưu Đồng', 70,
       'https://i.pinimg.com/736x/0b/66/23/0b66238e06411a9d1c7bbff2a30bb94e.jpg', 0.3, 'SKU-TTX-001',
       'Những câu chuyện buồn vui tuổi thanh xuân.', '2013-05-01', 'ACTIVE', 2, 2),

      (32, 'Vị Tu Sĩ Bán Chiếc Ferrari', 125000, 'ISBN-FERR-001', 'Robin Sharma', 65,
       'https://i.pinimg.com/736x/c2/df/3b/c2df3be14c45f6915a51016a2160c081.jpg', 0.4, 'SKU-FERR-001',
       'Câu chuyện truyền cảm hứng về ý nghĩa cuộc sống.', '1997-01-01', 'ACTIVE', 3, 1),

      (33, 'Thiên Tài Bên Trái, Kẻ Điên Bên Phải', 140000, 'ISBN-TTBD-001', 'Cao Minh', 50,
       'https://i.pinimg.com/736x/ea/77/0d/ea770d60563a88c897fa45af0de1a4e2.jpg', 0.45, 'SKU-TTBD-001',
       'Những cuộc đối thoại với bệnh nhân tâm thần.', '2014-03-01', 'ACTIVE', 4, 1),

      (34, 'Đời Ngắn Đừng Ngủ Dài', 95000, 'ISBN-DN-001', 'Robin Sharma', 80,
       'https://i.pinimg.com/736x/89/26/4d/89264d2be019994beb82bae841bacbb1.jpg', 0.35, 'SKU-DN-001',
       'Sống trọn vẹn từng ngày.', '2013-07-01', 'ACTIVE', 3, 1),

      (35, 'Tôi Thấy Hoa Hồng Trên Cỏ Xanh (Tranh Truyện)', 89000, 'ISBN-HVTT-001', 'Nguyễn Nhật Ánh', 75,
       'https://i.pinimg.com/736x/d0/05/95/d00595a95f3b1c6e6235db6cac0c8a63.jpg', 0.28, 'SKU-HVTT-001',
       'Phiên bản tranh truyện dễ thương.', '2018-09-10', 'ACTIVE', 5, 2),

      (36, 'Spy X Family Tập 1', 35000, 'ISBN-SPY-001', 'Tatsuya Endo', 160,
       'https://i.pinimg.com/736x/21/5e/5a/215e5a67ef177087d0af9f9ad788b705.jpg', 0.2, 'SKU-SPY-001',
       'Gia đình điệp viên siêu lầy lội.', '2019-07-04', 'ACTIVE', 1, 1),

      (37, 'Spy X Family Tập 2', 35000, 'ISBN-SPY-002', 'Tatsuya Endo', 150,
       'https://i.pinimg.com/736x/ea/56/65/ea56655ea291b8a8e4ea9a8f05bd59e4.jpg', 0.2, 'SKU-SPY-002',
       'Tập 2 tiếp tục những câu chuyện hài hước.', '2019-10-04', 'ACTIVE', 1, 1),

      (38, 'Thinking, Fast and Slow (Bản Tiếng Anh)', 220000, 'ISBN-TDNC-EN-001', 'Daniel Kahneman', 40,
       'https://i.pinimg.com/736x/f6/53/46/f653460ff9cf539441053568833f87b8.jpg', 0.7, 'SKU-TDNC-EN-001',
       'Bản tiếng Anh của Tư Duy Nhanh Và Chậm.', '2011-10-25', 'ACTIVE', 4, 1),

      (39, 'Sách Tô Màu Dành Cho Bé', 45000, 'ISBN-COLOR-001', 'Nhiều tác giả', 200,
       'https://i.pinimg.com/736x/95/44/3e/95443edef5c9a6e0f7a59458cdc8e90d.jpg', 0.25, 'SKU-COLOR-001',
       'Sách tô màu giúp bé phát triển sáng tạo.', '2020-01-01', 'ACTIVE', 5, 2),

      (40, 'Combo Tự Học Tiếng Anh Giao Tiếp', 250000, 'ISBN-EN-001', 'Nhiều tác giả', 55,
       'https://i.pinimg.com/736x/9e/07/9e/9e079e7e3e8ebefb5632c2c960f119f3.jpg', 0.9, 'SKU-EN-001',
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

INSERT INTO book_category (book_id, category_id) VALUES
-- 1,2: Doraemon – Truyện tranh + Thiếu nhi
(1, 2),
(1, 5),
(2, 2),
(2, 5),

-- 3: Tuổi Trẻ Đáng Giá Bao Nhiêu – Kỹ năng sống
(3, 4),

-- 4: Đắc Nhân Tâm – Kỹ năng sống
(4, 4),

-- 5: Nhà Giả Kim – Tiểu thuyết
(5, 1),

-- 6: Totto-chan Bên Cửa Sổ – Thiếu nhi
(6, 5),

-- 7: Cha Giàu Cha Nghèo – Kinh tế + Kỹ năng sống
(7, 3),
(7, 4),

-- 8: 7 Thói Quen Hiệu Quả – Kỹ năng sống
(8, 4),

-- 9,10: Sherlock Holmes – Tiểu thuyết
(9, 1),
(10, 1),

-- 11,12: One Piece – Truyện tranh
(11, 2),
(12, 2),

-- 13,14: Harry Potter – Tiểu thuyết + Thiếu nhi
(13, 1),
(13, 5),
(14, 1),
(14, 5),

-- 15: Tư Duy Nhanh Và Chậm – Kinh tế + Kỹ năng sống
(15, 3),
(15, 4),

-- 16: Nghĩ Giàu Làm Giàu – Kinh tế + Kỹ năng sống
(16, 3),
(16, 4),

-- 17: Mắt Biếc – Tiểu thuyết
(17, 1),

-- 18: Tôi Thấy Hoa Vàng Trên Cỏ Xanh – Thiếu nhi
(18, 5),

-- 19,20: Sapiens & Homo Deus – Kinh tế / non-fiction
(19, 3),
(20, 3);

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
