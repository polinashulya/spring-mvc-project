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
values ('test1', 'hairdresser')
ON CONFLICT (code) DO NOTHING;


/**
  categories
 */

insert into procedurecategories(id, name)
values (1, 'hair')
ON CONFLICT (id) DO NOTHING;
/**
  producers
 */

INSERT INTO procedures(code, description, duration, name, price, procedurecategories_id)
values ('test1', 'botox is great', 3, 'botox', 5, 1)
ON CONFLICT (code) DO NOTHING;