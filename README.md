# Sales Concepts

## How Sales Process Work in Cheetah Sales?

The steps below mostly describe B2B sales. In case of B2C, some of these steps are optional. 
For example an E-Commerce system may use this CRM as well. In that case when a customer signs up through E-Commerce portal, a contact and account information 
is created in CRM. E-Commerce may also use the CRM product catalog. When a user buys products, sales order details can also flow into CRM. 
The customer grievences can also be answered via the helpdesk module. 

### Lead Generation 

Generating leads is the first step in a sales process. There are various methods for sales prospecting. 
Everyday you send out a dozen or more sales prospecting emails and make a ton of cold calls in search of leads who might be interested in your solution. 
You receive queries from potential customers through emails and web form submissions. 
You find prospects seeking suggestions and advices on social channels before making a purchase. 

### Lead Qualification

Lead qualification is the process of evaluating prospect’s willingness to buy from you. 
Lead qualification helps you save time spent on prospects who never buy from you.
CRM’s profile scoring and engagement scoring features enable you to identify and focus on your most valuable prospects.[More on this later]


### Product Demonstration

After qualifying leads, the next step is to schedule an appointment with them to present your product or service. 
If you’re rallying emails back and forth to set an appointment, then you’re not only handling the process inefficiently 
but also slowing down the deal’s momentum


### Negotiation

You’ve delivered an impressive demo, your prospect is convinced to buy, you’ve sent a price quote and are waiting to close the deal. 
But then you hear the prospect say – “I’d like to negotiate”. 
While this stage of the sales cycle highly demands your negotiation skills, you can still use a few automations to manage documents and make quick decisions.

In cases of prospects asking for a discount that is over your approval limit, you have to consult with your manager or other higher level executive before 
sending the quote to the prospect. 
A CRM should have a quote approval process in place you don’t have to waste time chasing down approvers for getting quotes approved. 
It should also be possible to auto sends quotes for approval when certain entry conditions are met. 
You can also include multiple approvers and the quote will be sent to each approver automatically.

### Agreements Review

Getting contracts and sales documents signed in time is critical to close deals by the expected close date. After all, you don’t want to stall a deal because a contract you had couriered didn’t reach the key decision makers before they left for a week long business trip. 
To help you get contracts signed quickly and reliably, a CRM should integrate with secure e-sign service providers.

### Close

The final stage in the sales cycle is close. 
When the prospect finally buys your product or service, you have successfully closed a deal. 
You’ve turned a prospect into a customer. Once the deal is closed, you hand off the customer account to the billing team and customer support team.
The billing team will require customer and deal related information to generate invoice 
and the support team will also use the same information to answer customer queries. 

## Product

Products represent a stock of goods or services you sell.Products are used in quotes and sales order. 
[In future they can be used to build Inventory management as well]

### Product Fields

|Field|Description|
|--- |--- |
|Availability|Select if the item is in stock or not from the dropdown list|
|Category |The product category to which the new item belongs|
|Cost|The actual cost of the item. This will not appear on printed quotes|
|Currency|The currency of the given prices (Cost, List, Unit)|
|Date Available|Select the date of availability if the item is out of stock|
|Date Created|The date the product catalog record was created|
|Date Modified|The date the product catalog record was last modified|
|Date-Cost-Price|The starting date that the cost is valid|
|Default Pricing Formula|Select a formula from the dropdown list to arrive at the discount price for the Unit Price field. |
|Description|A description or other information about the product|
|List Price|The quotable list price of the product|
|Manufacturer Name|The manufacturer of the product|
|Mft. Part Number|The manufacturer's part number for the product|
|Product Name|The name of the product|
|Product URL|The web address of product if it is available online|
|Quantity in Stock|Enter the number of units that are in stock of the product Note: Negative values are supported in this field.|
|Support Contact|The support person's contact information, such as the phone number or email address|
|Support Desc.|Brief description or other information regarding the support provided|
|Support Name|The name of the Customer Support person|
|Support Term|The term (e.g. six months, one year, etc.) in which support will be provided for the product|
|Tags|User-created keywords that can be used to identify records in filters, dashlets, and reportsNote: For more information on creating and using tags, please refer to the Tags documentation.|
|Tax Class|Tax classification (e.g. taxable, non-taxable) for the product|
|Type|The specified product type|
|Unit Price|The unit price of the product|
|Vendor Part Number|The vendor's part number for the product|
|Weight|The weight of the product Note: Negative values are supported in this field.|



**Product Pricing Formulas**

The formulas are as follows:

*  Fixed Price: Allows you to enter a Unit Price without any calculation.

*  Profit Margin: Enter the points in the adjoining field to vary the percentage against the cost.

*  Markup over Cost: Choose a percentage to raise the price over the cost.

*  Discount from List: Enter the discount percentage from the List Price in the adjoining field.

*  Same as List: The Unit Price will be the same as the List Price.


### Product Category

Products are organized in categories. Categories are hierarchial in nature - ie a category can have parent category.

### Product Category Fields

|Field|Description|
|--- |--- |
|Product Category|The category's name as it will appear on the Product Category dropdown list|
|Parent Category|Select a parent product category if this product category is a sub-set of another category|
|Description|A description or other information about the product category|
|Order|	Enter a number to specify the order in which this category will appear in the Product Category dropdown list|


## Territory

**What is a territory?**

Territories define specific geographic regions that your company uses to organize and assign leads. 
For example, create a territory called “New England” that includes Massachusetts and Vermont, 
or “New York Metro” that includes everything within 25 miles of New York City.


**What can I do with territories?**

After you create one or more territories, you can use the Lead Distribution 
page to assign new leads in a territory to a specific user or team. You can also filter by territories from the leads, companies, or people tabs.


**Territory order**

Territories processes this list from the top down. Place your most precise territories at the top (e.g. cities and postal codes), 
and wider ranging territories at the bottom.


**Recalculating territories**

When you modify and save your territories, Cheetah will ask if you want to recalculate all territories using the new rules. 
Note that you can also manually change a person or company’s territory.



## Activity

Activity can be of different types

*  Phone call
*  Meeting
*  Virtual meetings


It is associated with lead, contacts, accounts

### Activity Fields

|Field|Description|
|--- |--- |
|Start date|Start date|
|Start time |Start time|
|End date|End date|
|End time|End time|
|Participants|List of contacts|
|Subject|Subject of the activity|
|Location|Meeting location or URL for web / online meeting|
|High priority|High priority|
|Status|Select a formula from the dropdown list to arrive at the discount price for the Unit Price field. |
|Agenda|A description or meeting agenda|
|Activity Log|Log important notes or action items from the activity|

The activity status has following values:

* Planned
* Held
* Not Held
* Cancelled
* Rescheduled
* Skipped

## Lead

### Converting Lead

Once a Lead is qualified and determined to be a potential sale, it is considered as a Hot Lead and can be converted to a Deal. 
A Contact record and Organization record will be created simultaneously to store the personal information and company information.


*  Any related records (Documents, Emails etc.,) can be transferred to either Organization or Contacts during the Lead Conversion.

*  Lead record will be marked to status converted. It will be available for view and cannot be converted again.

*  After conversion, Contact, Organization or Opportunity records cannot be reverted to the previous state.


### Lead conversion field mapping

| Lead Field      | Field Type | Contacts Field       | Organizations Field | Opportunities Field |
|-----------------|------------|----------------------|---------------------|---------------------|
| Annual Revenue  | Currency   | Annual Revenue       |                     |                     |
| City            | Text       | Shipping City        |                     |                     |
| City            | Text       | Billing City         | Mailing City        |                     |
| Company         |            | Organization Name    |                     | Opportunity Name    |
| Country         | Text       | Billing Country      | Mailing Country     |                     |
| Country         | Text       | Shipping Country     |                     |                     |
| Description     | Text Area  | Description          | Description         | Description         |
| Email           | Email      | Email                | Email               |                     |
| First Name      | Text       | First Name           | First Name          |                     |
| Industry        | Pick List  | Industry             |                     |                     |
| Last Name       | Text       | First Name           | First Name          |                     |
| Lead Source     | Pick List  |                      | Lead Source         | Lead Source         |
| No Of Employees | Integer    | Employees            |                     |                     |
| Phone           | Phone      | Phone                | Office Phone        |                     |
| PO Box          | Text       | Shipping PO Box      |                     |                     |
| PO Box          | Text       | Billing PO Box       | Mailing PO Box      |                     |
| Postal Code     | Text       | Billing Postal Code  |                     |                     |
| Postal Code     | Text       | Shipping Postal Code |                     |                     |
| Rating          | Pick List  | Rating               |                     |                     |
| Salutation      | Text       |                      | Salutation          |                     |
| Secondary Email | Email      |                      | Secondary Email     |                     |
| State           | Text       | Shipping State       |                     |                     |
| State           | Text       | Billing State        | Mailing State       |                     |
| Street          | Text Area  | Billing Address      | Mailing Street      |                     |
| Street          | Text Area  | Shipping Address     |                     |                     |
| Website         | URL        | Website              |                     |                     |
|                 |            |                      |                     |                     |

