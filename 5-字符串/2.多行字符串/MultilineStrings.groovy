/**
 * 定义多行字符串，只需要使用三个单引号声明「''' 多行字符串的内容 '''」，如果多行字符串需要包含变量，使用三个双引号定义即可「"""  """」
 *
 * @author Harry Zhang 
 * @since 2019/12/29 12:11 PM
 */

price = 251.12

message = '''
    第一行，
    第二行，
    第三行，
    变量: ${price}
'''
println message

message = """
    第一行，
    第二行，
    第三行，
    变量: ${price}
"""
println message
