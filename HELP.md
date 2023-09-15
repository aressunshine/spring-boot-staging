# Getting Started
SpringBoot 脚手架项目

## OpenAPI 3.0
| swagger2 | swagger3 |
| @Api | @Tag 或者 Tags|
| @ApiIgnore | @Parameter(hidden = true) 或 @Operation(hidden = true) 或 @Hidden |
| @ApiImplicitParam | @Parameter |
| @ApiImplicitParams | @Parameters |
| @ApiModel	| @Schema |
| @ApiModelProperty	| @Schema |
| @ApiOperation(value = “foo”, notes = “bar”) | @Operation(summary = “foo”, description = “bar”) |
| @ApiParam	| @Parameter |
| @ApiResponse(code = 404, message = “foo”)	| @ApiResponse(responseCode = “404”, description = “foo”) |

### 一、作用于类

1. @Tag：用于说明或定义的标签。也可以作用于方法上
```
部分参数：
name：名称
description：描述
```
2. @Hidden：某个元素（API 操作、实体类属性等）是否在 API 文档中隐藏。
3. @ApiResponse：API 的响应信息。也可以作用于方法上，一般常用于方法上
```
部分参数：

responseCode：响应的 HTTP 状态码
description：响应信息的描述
content：响应的内容

代码参考：

@ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = User.class)))
@ApiResponse(responseCode = "404", description = "查询失败")
@GetMapping("/users/{id}")
public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
    // ...
}
```

### 二、作用于方法上
1. @Schema：用于描述实体类属性的描述、示例、验证规则等，比如 POJO 类及属性
```
部分参数：

name：名称
title：标题
description：描述
example：示例值
required：是否为必须
format：属性的格式。如 @Schema(format = “email”)
maxLength、minLength：指定字符串属性的最大长度和最小长度
maximum、minimum：指定数值属性的最大值和最小值
pattern：指定属性的正则表达式模式
type： 数据类型（integer，long，float，double，string，byte，binary，boolean，date，dateTime，password），必须是字符串。
如 @Schema=(type=“integer”)
implementation ：具体的实现类，可以是类本身，也可以是父类或实现的接口

代码参考：

@Tag(name = "用户", description = "用户实体类")
@Data
public class User {

    @Schema(name = "用户id", type = "long")
    private Long id;
    @Schema(name = "用户名", type = "long")
    private String name;
    @Schema(name = "密码", type = "password")
    private String password;
}
```
2. @Operation：描述 API 操作的元数据信息。常用于 controller 上

```
部分参数：

summary：简短描述
description ：更详细的描述
hidden：是否隐藏
tags：标签，用于分组API
operationId：操作的唯一标识符，建议使用唯一且具有描述性的名称
parameters：指定相关的请求参数，使用 @Parameter 注解来定义参数的详细属性。
requestBody：指定请求的内容，使用 @RequestBody 注解來指定请求的类型。
responses：指定操作的返回内容，使用 @ApiResponse 注解定义返回值的详细属性。

代码参考：

@Operation(
  summary = "操作摘要", description = "操作的详细描述", operationId = "operationId", tags = "tag1",
  parameters = {
    @Parameter(name = "param1", description = "参数1", required = true),
    @Parameter(name = "param2", description = "参数2", required = false)
  },
  requestBody = @RequestBody(description = "请求描述",required = true,
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RequestBodyModel.class))
  ),
  responses = {
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))),
    @ApiResponse(responseCode = "400", description = "错误", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseModel.class)))
  }
)
@Tag(name = "标签1")
@ApiResponses(value = {
  @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))),
  @ApiResponse(responseCode = "400", description = "错误", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseModel.class)))
})
public void Operation() {
  // 逻辑
}
```
3. @Parameter：用于描述 API 操作中的参数

```
部分参数：

name : 指定的参数名
in：参数来源，可选 query、header、path 或 cookie，默认为空，表示忽略
ParameterIn.QUERY 请求参数
ParameterIn.PATH 路径参数
ParameterIn.HEADER header参数
ParameterIn.COOKIE cookie 参数
description：参数描述
required：是否必填，默认为 false
schema ：参数的数据类型。如 schema = @Schema(type = “string”)
```
4. @Parameters：包含多个 @Parameter 注解，指定多个参数
```
代码参考：

@Parameters({
  @Parameter(name = "param1", description = "description", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string")),
  @Parameter(name = "param2", description = "description", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
})
```
5. @RequestBody：API 请求的注解
```
部分参数：

description：请求信息的描述
content：请求的内容
required：是否必须
```
6. @Content
```
部分参数：

mediaType：内容的类型。比如：application/json
schema：内容的模型定义，使用 @Schema 注解指定模型的相关信息。
```

