/* User */
insert into user (id, username, password, first_name, last_name, email, vote) values
(1, 'demo'      , 'demo'     , 'Eduardo'  , 'Spinelli', 'teste@here.me'          , 0),
(2, 'admin'     , 'admin'    , 'Marcos'   , 'Castro'  , 'mcastro@here.me'        , 0),
(3, 'user'      , 'user'     , 'Virginia' , 'Reynolds', 'vreynolds0@slashdot.org', 0),;

/* Employees */
insert into restaurant (id, name, number_votes) values
(201, 'Harris'  ,0),
(202, 'Hayes'   ,0),
(203, 'Palmer'  ,0),
(204, 'Alvarez' ,0),
(205, 'Reynolds',0);

/* orders */
insert into winner (id, vote_date, restaurant_id) values
(4001, '2017-11-01', 204),
(4002, '2017-11-11', 204),
(4003, '2017-11-10', 205),
(4004, '2017-10-21', 204),
(4005, '2017-11-02', 201),
(4006, '2017-11-09', 202),
(4008, '2017-11-08', 205);
