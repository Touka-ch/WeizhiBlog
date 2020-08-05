<template>
  <el-card calss="el-card">
    <el-row>
      <div class="title">{{ article.title }}</div>
    </el-row>
    <el-row>
      <div style="text-align: center">
        <i class="el-icon-collection-tag">
          <span>：</span>
          <el-tag size="mini" style="font-size: 14px">swagger2</el-tag>
        </i>
        <i class="el-icon-time">
          <span>发表于：2019-03-24</span>
        </i>
        <i class="el-icon-view"></i>
        <span>12414次围观</span>
      </div>
    </el-row>
    <el-row>
      <!--<div v-html="article.data"></div>-->
      <mavon-editor :value="article.data" :subfield="false" :defaultOpen="'preview'" :toolbarsFlag="false" :editable="false" :scrollStyle="true" :ishljs="true" :boxShadow="false"></mavon-editor>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: 'CommonArticle',
  props: {},
  data() {
    return {
      content: '# your markdown content',
      article: {
        title: 'jhsadhsahdasd',
        data:
          '# 欢迎使用 Markdown在线编辑器 MdEditor\n' +
          '\n' +
          '**Markdown是一种轻量级的「标记语言」**\n' +
          '\n' +
          '\n' +
          '![markdown](https://www.mdeditor.com/images/logos/markdown.png "markdown")\n' +
          '\n' +
          '\n' +
          'Markdown是一种可以使用普通文本编辑器编写的标记语言，通过简单的标记语法，它可以使普通文本内容具有一定的格式。它允许人们使用易读易写的纯文本格式编写文档，然后转换成格式丰富的HTML页面，Markdown文件的后缀名便是“.md”\n' +
          '\n' +
          '\n' +
          '## MdEditor是一个在线编辑Markdown文档的编辑器\n' +
          '\n' +
          '*MdEditor扩展了Markdown的功能（如表格、脚注、内嵌HTML等等），以使让Markdown转换成更多的格式，和更丰富的展示效果，这些功能原初的Markdown尚不具备。*\n' +
          '\n' +
          '> Markdown增强版中比较有名的有Markdown Extra、MultiMarkdown、 Maruku等。这些衍生版本要么基于工具，如~~Pandoc~~，Pandao；要么基于网站，如GitHub和Wikipedia，在语法上基本兼容，但在一些语法和渲染效果上有改动。\n' +
          '\n' +
          'MdEditor源于Pandao的JavaScript开源项目，开源地址[Editor.md](https://github.com/pandao/editor.md "Editor.md")，并在MIT开源协议的许可范围内进行了优化，以适应广大用户群体的需求。向优秀的markdown开源编辑器原作者Pandao致敬。\n' +
          '\n' +
          '\n' +
          '![Pandao editor.md](https://pandao.github.io/editor.md/images/logos/editormd-logo-180x180.png "Pandao editor.md")\n' +
          '\n' +
          '\n' +
          '\n' +
          '## MdEditor的功能列表演示\n' +
          '\n' +
          '# 标题H1\n' +
          '\n' +
          '## 标题H2\n' +
          '\n' +
          '### 标题H3\n' +
          '\n' +
          '#### 标题H4\n' +
          '\n' +
          '##### 标题H5\n' +
          '\n' +
          '###### 标题H5\n' +
          '\n' +
          '### 字符效果和横线等\n' +
          '----\n' +
          '\n' +
          '~~删除线~~ <s>删除线（开启识别HTML标签时）</s>\n' +
          '\n' +
          '*斜体字*      _斜体字_\n' +
          '\n' +
          '**粗体**  __粗体__\n' +
          '\n' +
          '***粗斜体*** ___粗斜体___\n' +
          '\n' +
          '上标：X<sub>2</sub>，下标：O<sup>2</sup>\n' +
          '\n' +
          '**缩写(同HTML的abbr标签)**\n' +
          '> 即更长的单词或短语的缩写形式，前提是开启识别HTML标签时，已默认开启\n' +
          '\n' +
          'The <abbr title="Hyper Text Markup Language">HTML</abbr> specification is maintained by the <abbr title="World Wide Web Consortium">W3C</abbr>.\n' +
          '### 引用 Blockquotes\n' +
          '\n' +
          '> 引用文本 Blockquotes\n' +
          '\n' +
          '引用的行内混合 Blockquotes\n' +
          '\n' +
          '> 引用：如果想要插入空白换行`即<br />标签`，在插入处先键入两个以上的空格然后回车即可，[普通链接](https://www.mdeditor.com/)。\n' +
          '\n' +
          '### 锚点与链接 Links\n' +
          '[普通链接](https://www.mdeditor.com/)\n' +
          '[普通链接带标题](https://www.mdeditor.com/ "普通链接带标题")\n' +
          '直接链接：<https://www.mdeditor.com>\n' +
          '[锚点链接][anchor-id]\n' +
          '[anchor-id]: https://www.mdeditor.com/\n' +
          '[mailto:test.test@gmail.com](mailto:test.test@gmail.com)\n' +
          'GFM a-tail link @pandao\n' +
          '邮箱地址自动链接 test.test@gmail.com  www@vip.qq.com\n' +
          '> @pandao\n' +
          '\n' +
          '### 多语言代码高亮 Codes\n' +
          '\n' +
          '#### 行内代码 Inline code\n' +
          '\n' +
          '\n' +
          '执行命令：`npm install marked`\n' +
          '\n' +
          '#### 缩进风格\n' +
          '\n' +
          '即缩进四个空格，也做为实现类似 `<pre>` 预格式化文本 ( Preformatted Text ) 的功能。\n' +
          '\n' +
          '    <?php\n' +
          '        echo "Hello world!";\n' +
          '    ?>\n' +
          '预格式化文本：\n' +
          '\n' +
          '    | First Header  | Second Header |\n' +
          '    | ------------- | ------------- |\n' +
          '    | Content Cell  | Content Cell  |\n' +
          '    | Content Cell  | Content Cell  |\n' +
          '\n' +
          '#### JS代码\n' +
          '```javascript\n' +
          'function test() {\n' +
          '\tconsole.log("Hello world!");\n' +
          '}\n' +
          '```\n' +
          '\n' +
          '#### HTML 代码 HTML codes\n' +
          '```html\n' +
          '<!DOCTYPE html>\n' +
          '<html>\n' +
          '    <head>\n' +
          '        <mate charest="utf-8" />\n' +
          '        <meta name="keywords" content="Editor.md, Markdown, Editor" />\n' +
          '        <title>Hello world!</title>\n' +
          '        <style type="text/css">\n' +
          '            body{font-size:14px;color:#444;font-family: "Microsoft Yahei", Tahoma, "Hiragino Sans GB", Arial;background:#fff;}\n' +
          '            ul{list-style: none;}\n' +
          '            img{border:none;vertical-align: middle;}\n' +
          '        </style>\n' +
          '    </head>\n' +
          '    <body>\n' +
          '        <h1 class="text-xxl">Hello world!</h1>\n' +
          '        <p class="text-green">Plain text</p>\n' +
          '    </body>\n' +
          '</html>\n' +
          '```\n' +
          '### 图片 Images\n' +
          '\n' +
          '图片加链接 (Image + Link)：\n' +
          '\n' +
          '\n' +
          '[![](https://www.mdeditor.com/images/logos/markdown.png)](https://www.mdeditor.com/images/logos/markdown.png "markdown")\n' +
          '\n' +
          '> Follow your heart.\n' +
          '\n' +
          '----\n' +
          '### 列表 Lists\n' +
          '\n' +
          '#### 无序列表（减号）Unordered Lists (-)\n' +
          '\n' +
          '- 列表一\n' +
          '- 列表二\n' +
          '- 列表三\n' +
          '\n' +
          '#### 无序列表（星号）Unordered Lists (*)\n' +
          '\n' +
          '* 列表一\n' +
          '* 列表二\n' +
          '* 列表三\n' +
          '\n' +
          '#### 无序列表（加号和嵌套）Unordered Lists (+)\n' +
          '+ 列表一\n' +
          '+ 列表二\n' +
          '    + 列表二-1\n' +
          '    + 列表二-2\n' +
          '    + 列表二-3\n' +
          '+ 列表三\n' +
          '    * 列表一\n' +
          '    * 列表二\n' +
          '    * 列表三\n' +
          '\n' +
          '#### 有序列表 Ordered Lists (-)\n' +
          '\n' +
          '1. 第一行\n' +
          '2. 第二行\n' +
          '3. 第三行\n' +
          '\n' +
          '#### GFM task list\n' +
          '\n' +
          '- [x] GFM task list 1\n' +
          '- [x] GFM task list 2\n' +
          '- [ ] GFM task list 3\n' +
          '    - [ ] GFM task list 3-1\n' +
          '    - [ ] GFM task list 3-2\n' +
          '    - [ ] GFM task list 3-3\n' +
          '- [ ] GFM task list 4\n' +
          '    - [ ] GFM task list 4-1\n' +
          '    - [ ] GFM task list 4-2\n' +
          '\n' +
          '----\n' +
          '\n' +
          '### 绘制表格 Tables\n' +
          '\n' +
          '| 项目        | 价格   |  数量  |\n' +
          '| --------   | -----:  | :----:  |\n' +
          '| 计算机      | $1600   |   5     |\n' +
          '| 手机        |   $12   |   12   |\n' +
          '| 管线        |    $1    |  234  |\n' +
          '\n' +
          'First Header  | Second Header\n' +
          '------------- | -------------\n' +
          'Content Cell  | Content Cell\n' +
          'Content Cell  | Content Cell\n' +
          '\n' +
          '| First Header  | Second Header |\n' +
          '| ------------- | ------------- |\n' +
          '| Content Cell  | Content Cell  |\n' +
          '| Content Cell  | Content Cell  |\n' +
          '\n' +
          '| Function name | Description                    |\n' +
          '| ------------- | ------------------------------ |\n' +
          '| `help()`      | Display the help window.       |\n' +
          '| `destroy()`   | **Destroy your computer!**     |\n' +
          '\n' +
          '| Left-Aligned  | Center Aligned  | Right Aligned |\n' +
          '| :------------ |:---------------:| -----:|\n' +
          '| col 3 is      | some wordy text | $1600 |\n' +
          '| col 2 is      | centered        |   $12 |\n' +
          '| zebra stripes | are neat        |    $1 |\n' +
          '\n' +
          '| Item      | Value |\n' +
          '| --------- | -----:|\n' +
          '| Computer  | $1600 |\n' +
          '| Phone     |   $12 |\n' +
          '| Pipe      |    $1 |\n' +
          '\n' +
          '----\n' +
          '\n' +
          '#### 特殊符号 HTML Entities Codes\n' +
          '\n' +
          '&copy; &  &uml; &trade; &iexcl; &pound;\n' +
          '&amp; &lt; &gt; &yen; &euro; &reg; &plusmn; &para; &sect; &brvbar; &macr; &laquo; &middot;\n' +
          '\n' +
          'X&sup2; Y&sup3; &frac34; &frac14;  &times;  &divide;   &raquo;\n' +
          '\n' +
          '18&ordm;C  &quot;  &apos;\n' +
          '\n' +
          '[========]\n' +
          '\n' +
          '### Emoji表情 :smiley:\n' +
          '\n' +
          '> Blockquotes :star:\n' +
          '\n' +
          '#### GFM task lists & Emoji & fontAwesome icon emoji & editormd logo emoji :editormd-logo-5x:\n' +
          '\n' +
          '- [x] :smiley: @mentions, :smiley: #refs, [links](), **formatting**, and <del>tags</del> supported :editormd-logo:;\n' +
          '- [x] list syntax required (any unordered or ordered list supported) :editormd-logo-3x:;\n' +
          '- [x] [ ] :smiley: this is a complete item :smiley:;\n' +
          '- [ ] []this is an incomplete item [test link](#) :fa-star: @pandao;\n' +
          '- [ ] [ ]this is an incomplete item :fa-star: :fa-gear:;\n' +
          '    - [ ] :smiley: this is an incomplete item [test link](#) :fa-star: :fa-gear:;\n' +
          '    - [ ] :smiley: this is  :fa-star: :fa-gear: an incomplete item [test link](#);\n' +
          '\n' +
          '#### 反斜杠 Escape\n' +
          '\n' +
          '\\*literal asterisks\\*\n' +
          '\n' +
          '[========]\n' +
          '### 科学公式 TeX(KaTeX)\n' +
          '\n' +
          '$$E=mc^2$$\n' +
          '\n' +
          '行内的公式$$E=mc^2$$行内的公式，行内的$$E=mc^2$$公式。\n' +
          '\n' +
          '$$x > y$$\n' +
          '\n' +
          '$$\\(\\sqrt{3x-1}+(1+x)^2\\)$$\n' +
          '\n' +
          '$$\\sin(\\alpha)^{\\theta}=\\sum_{i=0}^{n}(x^i + \\cos(f))$$\n' +
          '\n' +
          '多行公式：\n' +
          '\n' +
          '```math\n' +
          '\\displaystyle\n' +
          '\\left( \\sum\\_{k=1}^n a\\_k b\\_k \\right)^2\n' +
          '\\leq\n' +
          '\\left( \\sum\\_{k=1}^n a\\_k^2 \\right)\n' +
          '\\left( \\sum\\_{k=1}^n b\\_k^2 \\right)\n' +
          '```\n' +
          '```katex\n' +
          '\\displaystyle\n' +
          '    \\frac{1}{\n' +
          '        \\Bigl(\\sqrt{\\phi \\sqrt{5}}-\\phi\\Bigr) e^{\n' +
          '        \\frac25 \\pi}} = 1+\\frac{e^{-2\\pi}} {1+\\frac{e^{-4\\pi}} {\n' +
          '        1+\\frac{e^{-6\\pi}}\n' +
          '        {1+\\frac{e^{-8\\pi}}\n' +
          '         {1+\\cdots} }\n' +
          '        }\n' +
          '    }\n' +
          '```\n' +
          '```latex\n' +
          'f(x) = \\int_{-\\infty}^\\infty\n' +
          '    \\hat f(\\xi)\\,e^{2 \\pi i \\xi x}\n' +
          '    \\,d\\xi\n' +
          '```\n' +
          '### 分页符 Page break\n' +
          '\n' +
          '> Print Test: Ctrl + P\n' +
          '\n' +
          '[========]\n' +
          '\n' +
          '### 绘制流程图 Flowchart\n' +
          '\n' +
          '```flow\n' +
          'st=>start: 用户登陆\n' +
          'op=>operation: 登陆操作\n' +
          'cond=>condition: 登陆成功 Yes or No?\n' +
          'e=>end: 进入后台\n' +
          '\n' +
          'st->op->cond\n' +
          'cond(yes)->e\n' +
          'cond(no)->op\n' +
          '```\n' +
          '[========]\n' +
          '\n' +
          '### 绘制序列图 Sequence Diagram\n' +
          '\n' +
          '```seq\n' +
          'Andrew->China: Says Hello\n' +
          'Note right of China: China thinks\\nabout it\n' +
          'China-->Andrew: How are you?\n' +
          'Andrew->>China: I am good thanks!\n' +
          '```\n' +
          '### End'
      }
    }
  }
}
</script>

<style scoped>
.title {
  text-align: center;
  font-size: 30px;
  font-family: 'Microsoft YaHei';
  font-weight: bold;
  margin-bottom: 60px;
}
.el-card {
  margin: 10px 0 30px 0;
  position: relative;
}
</style>
