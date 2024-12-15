variable "resource_group_name" {
  type        = string
  description = "Name of the resource group"
}

variable "resource_group_location" {
  type        = string
  description = "Location of the resource group"
}


variable "app_service_plan_name" {
  type        = string
  description = "App service plan name"
}

variable "app_service_name" {
  type        = string
  description = "Name of app"
}

variable "sql_server_name" {
  type        = string
  description = "Name of sql server"
}

variable "sql_database_name" {
  type        = string
  description = "Name of database"
}

variable "sql_admin_login" {
  description = "SQL Admin Login"
  type        = string
}


variable "sql_admin_password" {
  description = "SQL Admin Password"
  type        = string
}


variable "firewall_rule_name" {
  description = "Name of the firewall rule"
  type        = string
}

variable "url" {
  description = "Repository URL"
  type        = string
}