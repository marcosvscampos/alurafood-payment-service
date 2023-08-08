insert into cards (id, `number`, expiration, code)
select uuid(), p.`number` , p.expiration , p.code  from payments p;