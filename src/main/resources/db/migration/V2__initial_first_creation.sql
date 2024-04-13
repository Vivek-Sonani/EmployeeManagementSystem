create table employee(
  employ_id Integer not null primary key,
  firstname varchar(50),
  middlename varchar(50),
  lastname varchar(50),
  email varchar(50),
  position varchar(50),
  contact_no int,
  salary float,
  created_date date,
  modified_date date,
  created_by varchar(50),
  modified_by varchar(50)
  );



  create table address(
    id Integer not null primary key,
    city varchar(50),
    state varchar(50),
    employee_id Integer,
    constraint emp_address_fk foreign key(employee_id)
    references employee(employ_id)
    );
 //   ALTER TABLE address
 //  ADD COLUMN modified_by VARCHAR(55);
    
    create table family_info(
      id Integer not null primary key,
      name varchar(50),
      relationship enum('FATHER', 'MOTHER', 'BROTHER'),
      dob date,
      occupation varchar(50),
      employee_id Integer,
      created_date date,
      modified_date date,
      constraint emp_family_fk foreign key(employee_id)
    references employee(employ_id) on delete cascade
    );
    
