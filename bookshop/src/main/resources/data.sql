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
