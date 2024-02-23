INSERT INTO user_roles (name)
VALUES ('CLIENT'),
       ('EMPLOYEE'),
       ('ADMIN')
ON CONFLICT (name) DO NOTHING;


/**
  countries
 */

insert into countries(id, name)
values (1, 'Belarus')
ON CONFLICT (id) DO NOTHING;;


/**
  positions
 */
INSERT INTO employee_positions(code, name)
values ('test', 'cleaner')
ON CONFLICT (code) DO NOTHING;


/**
  categories
 */

insert into procedurecategories(id, name)
values (1, 'test1')
ON CONFLICT (id) DO NOTHING;
/**
  producers
 */

INSERT INTO procedures(code, description, duration, name, price, procedurecategories_id)
values ('test', 'blabla', 3, 'test1', 5, 1)
ON CONFLICT (code) DO NOTHING;