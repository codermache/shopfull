/****** Query  ******/
delete from [order]
delete from [order_detail]
delete from [bill]

select * from [order]
select * from [order_detail]
select * from [bill]
select * from [product]

-- query select bill for admin
select b.id, u.fullname as [Customer Name], b.created_date,
u.address, u.email, u.phone,
SUM(price * product_quantity) as [Total], b.[status] from [bill] b
join [order_detail] od on od.order_id = b.order_id
join [user] u on u.id = b.[user_id]
where b.status = 'done'
group by b.id, u.fullname , b.created_date, b.[status], u.address, u.email, u.phone

-- query select bill detail for customer
select b.id, u.fullname as [CustomerName], b.created_date as [CreatedDate], p.[name] as [ProductName],
image_url, product_quantity as [Quantity], p.price, (p.price * product_quantity) as SubTotal from [bill] b
join [user] u on u.id = b.[user_id]
join [order_detail] od on od.order_id = b.order_id
join [product] p on p.id = od.product_id
where b.id = 72

--unban an user
UPDATE [dbo].[user]
   SET [banned] = null
 WHERE id = 7


-- update status
UPDATE [dbo].[bill]
   SET [status] = 'process'
 WHERE id = 75
