set foreign_key_checks = 0;

delete from `groups`;
delete from `permission`;
delete from `group_permission`;
delete from `recipe`;
delete from `tag`;
delete from `user`;
delete from `user_group`;
delete from `oauth_client_details`;

set foreign_key_checks = 1;

insert into `user` (`id`, `name`,`email`,`password`,`phone`,`role`,`created_at`, `active`, `location_country`, `location_state`,`location_city`,`bank_account_bank_code`,`bank_account_agency`,`bank_account_number`,`bank_account_digit`,`bank_account_type`,`birthdate`) values
('f572f96c-5b59-4695-98ce-29ff068b8942', 'Gabriel Rodrigues', 'gabriel.rodrigues@bonnafood.com.br', '$2a$12$Ch7QFrw537HM73FYrdsWw.lB2pKpWekyaVITtt/ytdFT2eIgr9Ow6', '(19) 98343-8754', 'MANAGER', '2019-11-03 02:01:21', true, 'Brazil', 'SP', 'Sumaré', '4', '123', '27993', '42', 'SAVING', '1999-07-08'),
('079f609f-dc8f-488d-a4b9-cd7d71070f32', 'Gabrily Souza da Paz', 'gabriely.souza@bonnafood.com.br', '$2a$12$QnuSg1.pRwK6VDnez5v1x.tGQOjgGP5D6zeF3G.Iny.lU/epqHCf6', '(19) 99999-9999', 'USER', utc_timestamp, true, 'Brazil', 'SP', 'Sumaré', '4', '123', '27993', '42', 'SAVING', '1999-02-13'),
('a09a3885-088c-4025-b92b-1a3c70271c0d', 'Ted Mosby', 'ted.mosby@bonnafood.com.br', '$2a$12$QnuSg1.pRwK6VDnez5v1x.tGQOjgGP5D6zeF3G.Iny.lU/epqHCf6', '(99) 99999-9999', 'EDITOR', utc_timestamp, true, 'Brazil', 'SP', 'Campinas', '4', '123', '27993', '42', 'SAVING', '1993-10-28'),
('cee7196b-dcd8-4064-9b9b-f0e659ed739b', 'Lili Aldrim', 'lili.aldrim@bonnafood.com.br', '$2a$12$QnuSg1.pRwK6VDnez5v1x.tGQOjgGP5D6zeF3G.Iny.lU/epqHCf6', '(99) 99999-9999', 'EDITOR', utc_timestamp, true, 'Brazil', 'SP', 'Sumaré', '4', '123', '27993', '42', 'SAVING', '1993-10-28');

insert into `permission` (`id`, `name`, `description`) values
('2b46e479-58cc-48e5-a084-afd2c8770984', 'VIEW_USERS', 'Visualizar usuários'),
('89eef0e0-739e-45d0-b222-d1c883ad1a58', 'EDIT_USERS', 'Editar usuários'),
('a1e0074d-b6b2-4aa9-87c5-184306caf715', 'CREATE_USERS', 'Criar usuários'),
('5a49e470-a87b-4e6f-809b-bfa336f29b70', 'VIEW_RECIPES', 'Visualizar receitas'),
('df9aebf7-c674-4eef-9932-802f57f58b9c', 'EDIT_RECIPES', 'Editar receitas'),
('3b247902-0b04-4f99-9ec0-8140bacdfa13', 'CREATE_RECIPES', 'Criar receitas'),
('15c41111-c8b1-46ba-8437-88dd480810e5', 'DELETE_RECIPES', 'Apagar receitas'),
('efe1fc45-3929-4ac9-ad9d-3d2934aa7f10', 'ADMIN', 'Administrador do sistema');

insert into `groups` (`id`, `name`) values
('536c8fe4-8ca7-4c99-b64a-bf467e0c468e', 'EDITOR'),
('4aeea182-1ef2-48f3-888e-cd7551a562af', 'REGISTER'),
('76b3a08f-a61b-41a6-b8ea-a255217d64ee', 'MANAGER');

insert into `group_permission` (`group_id`, `permission_id`) values
    ('76b3a08f-a61b-41a6-b8ea-a255217d64ee', 'efe1fc45-3929-4ac9-ad9d-3d2934aa7f10'),
    ('76b3a08f-a61b-41a6-b8ea-a255217d64ee', '15c41111-c8b1-46ba-8437-88dd480810e5'),
    ('76b3a08f-a61b-41a6-b8ea-a255217d64ee', '3b247902-0b04-4f99-9ec0-8140bacdfa13'),
    ('76b3a08f-a61b-41a6-b8ea-a255217d64ee', 'df9aebf7-c674-4eef-9932-802f57f58b9c'),
    ('76b3a08f-a61b-41a6-b8ea-a255217d64ee', 'a1e0074d-b6b2-4aa9-87c5-184306caf715'),
    ('76b3a08f-a61b-41a6-b8ea-a255217d64ee', '89eef0e0-739e-45d0-b222-d1c883ad1a58'),
    ('76b3a08f-a61b-41a6-b8ea-a255217d64ee', '2b46e479-58cc-48e5-a084-afd2c8770984'),
    ('4aeea182-1ef2-48f3-888e-cd7551a562af', 'a1e0074d-b6b2-4aa9-87c5-184306caf715'),
    ('4aeea182-1ef2-48f3-888e-cd7551a562af', '3b247902-0b04-4f99-9ec0-8140bacdfa13'),
    ('536c8fe4-8ca7-4c99-b64a-bf467e0c468e', '89eef0e0-739e-45d0-b222-d1c883ad1a58'),
    ('536c8fe4-8ca7-4c99-b64a-bf467e0c468e', 'df9aebf7-c674-4eef-9932-802f57f58b9c');

insert into `user_group` (`user_id`, `group_id`) values
    ('f572f96c-5b59-4695-98ce-29ff068b8942', '76b3a08f-a61b-41a6-b8ea-a255217d64ee'),
    ('079f609f-dc8f-488d-a4b9-cd7d71070f32', '536c8fe4-8ca7-4c99-b64a-bf467e0c468e'),
    ('079f609f-dc8f-488d-a4b9-cd7d71070f32', '4aeea182-1ef2-48f3-888e-cd7551a562af'),
    ('a09a3885-088c-4025-b92b-1a3c70271c0d', '536c8fe4-8ca7-4c99-b64a-bf467e0c468e'),
    ('cee7196b-dcd8-4064-9b9b-f0e659ed739b', '4aeea182-1ef2-48f3-888e-cd7551a562af');

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove)
values ( 'bonnafood', null, '$2y$12$fHixriC7yXX/i1/CmpnGH.RFyK/l5YapLCFOEbIktONjE8ZDykSnu', 'READ,WRITE', 'password,client_credentials', null, null, null, null, null);