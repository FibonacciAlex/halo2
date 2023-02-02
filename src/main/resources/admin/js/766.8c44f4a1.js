"use strict";(self["webpackChunkhalo_admin"]=self["webpackChunkhalo_admin"]||[]).push([[766],{24766:function(t,e,a){a.r(e),a.d(e,{default:function(){return y}});var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("page-view",[a("a-row",[a("a-col",{attrs:{span:24}},[a("a-card",{attrs:{bodyStyle:{padding:"16px"},bordered:!1}},[a("div",{staticClass:"table-page-search-wrapper"},[a("a-form",{attrs:{layout:"inline"}},[a("a-row",{attrs:{gutter:48}},[a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"关键词："}},[a("a-input",{on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleQuery()}},model:{value:t.list.params.keyword,callback:function(e){t.$set(t.list.params,"keyword",e)},expression:"list.params.keyword"}})],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"状态："}},[a("a-select",{attrs:{placeholder:"请选择状态"},on:{change:function(e){return t.handleQuery()}},model:{value:t.list.params.type,callback:function(e){t.$set(t.list.params,"type",e)},expression:"list.params.type"}},t._l(Object.keys(t.list.journalType),(function(e){return a("a-select-option",{key:e,attrs:{value:e}},[t._v(" "+t._s(t.list.journalType[e].text)+" ")])})),1)],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons"},[a("a-space",[a("a-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("查询")]),a("a-button",{on:{click:function(e){return t.handleResetParam()}}},[t._v("重置")])],1)],1)])],1)],1)],1),a("div",{staticClass:"table-operator"},[a("a-button",{attrs:{icon:"plus",type:"primary"},on:{click:t.handleOpenPublishModal}},[t._v("写日志")])],1),a("a-divider"),a("div",{staticClass:"mt-4"},[t.list.loading||0!==t.list.data.length?a("a-list",{attrs:{dataSource:t.list.data,loading:t.list.loading,pagination:!1,itemLayout:"vertical"},scopedSlots:t._u([{key:"renderItem",fn:function(e,n){return[a("a-list-item",{key:n,scopedSlots:t._u([{key:"actions",fn:function(){return[a("a-button",{staticClass:"!p-0",attrs:{type:"link"}},[a("a-icon",{attrs:{type:"like-o"}}),t._v(" "+t._s(e.likes)+" ")],1),a("a-button",{staticClass:"!p-0",attrs:{type:"link"},on:{click:function(a){return t.handleOpenJournalCommentsDrawer(e)}}},[a("a-icon",{attrs:{type:"message"}}),t._v(" "+t._s(e.commentCount)+" ")],1),"INTIMATE"===e.type?a("a-button",{staticClass:"!p-0",staticStyle:{color:"grey"},attrs:{type:"link"},on:{click:function(a){return t.handleJournalTypeUpdate(e)}}},[a("a-icon",{attrs:{type:"lock"}})],1):a("a-button",{staticClass:"!p-0",attrs:{type:"link"},on:{click:function(a){return t.handleJournalTypeUpdate(e)}}},[a("a-icon",{attrs:{type:"unlock"}})],1)]},proxy:!0},{key:"extra",fn:function(){return[a("a-button",{staticClass:"!p-0",attrs:{type:"link"},on:{click:function(a){return t.handleOpenEditModal(e)}}},[t._v("编辑")]),a("a-divider",{attrs:{type:"vertical"}}),a("a-popconfirm",{attrs:{cancelText:"取消",okText:"确定",title:"你确定要删除这条日志？"},on:{confirm:function(a){return t.handleDelete(e.id)}}},[a("a-button",{staticClass:"!p-0",attrs:{type:"link"}},[t._v("删除")])],1)]},proxy:!0}],null,!0)},[a("a-list-item-meta",{scopedSlots:t._u([{key:"description",fn:function(){return[a("div",{staticClass:"journal-list-content",domProps:{innerHTML:t._s(e.content)}})]},proxy:!0},{key:"title",fn:function(){return[a("span",[t._v(t._s(t._f("moment")(e.createTime)))])]},proxy:!0},{key:"avatar",fn:function(){return[a("a-avatar",{attrs:{src:t.user.avatar,size:"large"}})]},proxy:!0}],null,!0)})],1)]}}])},[a("div",{staticClass:"page-wrapper"},[a("a-pagination",{staticClass:"pagination",attrs:{current:t.pagination.page,defaultPageSize:t.pagination.size,pageSizeOptions:["10","20","50","100"],total:t.pagination.total,showLessItems:"",showSizeChanger:""},on:{change:t.handlePageChange,showSizeChange:t.handlePageSizeChange}})],1)]):a("a-empty")],1)],1)],1)],1),a("div",{staticStyle:{position:"fixed",bottom:"30px",right:"30px"}},[a("a-button",{attrs:{icon:"setting",shape:"circle",size:"large",type:"primary"},on:{click:function(e){t.optionModal.visible=!0}}})],1),a("a-modal",{attrs:{afterClose:function(){return t.optionModal.visible=!1},title:"页面设置"},scopedSlots:t._u([{key:"footer",fn:function(){return[a("a-button",{key:"submit",attrs:{type:"primary"},on:{click:function(e){return t.handleSaveOptions()}}},[t._v("保存")])]},proxy:!0}]),model:{value:t.optionModal.visible,callback:function(e){t.$set(t.optionModal,"visible",e)},expression:"optionModal.visible"}},[a("a-form",{attrs:{layout:"vertical"}},[a("a-form-item",{attrs:{help:"* 需要主题进行适配",label:"页面标题："}},[a("a-input",{model:{value:t.optionModal.options.journals_title,callback:function(e){t.$set(t.optionModal.options,"journals_title",e)},expression:"optionModal.options.journals_title"}})],1),a("a-form-item",{attrs:{label:"每页显示条数："}},[a("a-input-number",{staticStyle:{width:"100%"},model:{value:t.optionModal.options.journals_page_size,callback:function(e){t.$set(t.optionModal.options,"journals_page_size",e)},expression:"optionModal.options.journals_page_size"}})],1)],1)],1),a("a-modal",{attrs:{title:t.formTitle,width:820},scopedSlots:t._u([{key:"footer",fn:function(){return[a("ReactiveButton",{attrs:{errored:t.form.saveErrored,loading:t.form.saving,erroredText:"发布失败",loadedText:"发布成功",text:"发布",type:"primary"},on:{callback:t.handleSaveOrUpdateCallback,click:t.handleSaveOrUpdate}})]},proxy:!0}]),model:{value:t.form.visible,callback:function(e){t.$set(t.form,"visible",e)},expression:"form.visible"}},[a("a-form-model",{ref:"journalForm",attrs:{model:t.form.model,rules:t.form.rules,layout:"vertical"}},[a("a-form-model-item",{attrs:{prop:"sourceContent"}},[a("div",{staticStyle:{height:"520px"},attrs:{id:"editor"}},[t.form.visible?a("MarkdownEditor",{attrs:{originalContent:t.form.model.sourceContent,subfield:!1,toolbars:t.simpleEditorToolbars},on:{"update:originalContent":function(e){return t.$set(t.form.model,"sourceContent",e)},"update:original-content":function(e){return t.$set(t.form.model,"sourceContent",e)},change:t.onContentChange}}):t._e()],1)]),a("a-form-model-item",[a("a-switch",{attrs:{checkedChildren:"公开",defaultChecked:"",unCheckedChildren:"私密"},model:{value:t.form.isPublic,callback:function(e){t.$set(t.form,"isPublic",e)},expression:"form.isPublic"}})],1)],1)],1),a("TargetCommentListModal",{attrs:{"target-id":t.list.selected.id,title:"「"+t.$options.filters.moment(t.list.selected.createTime)+"」的评论",visible:t.journalCommentDrawer.visible,target:"journal"},on:{"update:visible":function(e){return t.$set(t.journalCommentDrawer,"visible",e)},close:t.onJournalCommentsDrawerClose},scopedSlots:t._u([{key:"extraFooter",fn:function(){return[a("a-button",{attrs:{disabled:t.selectPreviousButtonDisabled},on:{click:t.handleSelectPrevious}},[t._v(" 上一篇")]),a("a-button",{attrs:{disabled:t.selectNextButtonDisabled},on:{click:t.handleSelectNext}},[t._v(" 下一篇")])]},proxy:!0}])})],1)},i=[],o=a(54288),r=a(22373),s=(a(70315),a(15928),a(31875),a(41479),a(23006)),l=a(2756),u=a(94370),c=a(98906),d=a(43376),p=a(22573),m=a(36591),f=a(58799),h={mixins:[u.jB,u.KT],components:{MarkdownEditor:f.Z,PageView:s.B4,TargetCommentListModal:l.Z},data:function(){return{simpleEditorToolbars:d.o6,list:{data:[],loading:!1,total:0,params:{page:0,size:10,keyword:void 0,type:void 0},hasPrevious:!1,hasNext:!1,selected:{},journalType:{PUBLIC:{text:"公开"},INTIMATE:{text:"私密"}}},form:{model:{},rules:{sourceContent:[{required:!0,message:"* 内容不能为空",trigger:[]}]},visible:!1,saving:!1,saveErrored:!1,isPublic:!0},journalCommentDrawer:{visible:!1},optionModal:{visible:!1,options:{}}}},beforeMount:function(){this.handleListJournals(),this.handleListOptions()},computed:(0,r.Z)((0,r.Z)({},(0,c.Se)(["user"])),{},{formTitle:function(){return this.form.model.id?"编辑":"发表"},pagination:function(){return{page:this.list.params.page+1,size:this.list.params.size,total:this.list.total}},selectPreviousButtonDisabled:function(){var t=this,e=this.list.data.findIndex((function(e){return e.id===t.list.selected.id}));return 0===e&&!this.list.hasPrevious},selectNextButtonDisabled:function(){var t=this,e=this.list.data.findIndex((function(e){return e.id===t.list.selected.id}));return e===this.list.data.length-1&&!this.list.hasNext}}),methods:(0,r.Z)((0,r.Z)({},(0,c.nv)(["refreshOptionsCache"])),{},{handleListJournals:function(){var t=this;return(0,o.Z)(regeneratorRuntime.mark((function e(){var a,n;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,t.list.loading=!0,e.next=4,m.Z.journal.list(t.list.params);case 4:if(a=e.sent,n=a.data,!(0===n.content.length&&t.list.params.page>0)){e.next=11;break}return t.list.params.page--,e.next=10,t.handleListJournals();case 10:return e.abrupt("return");case 11:t.list.data=n.content,t.list.total=n.total,t.list.hasPrevious=n.hasPrevious,t.list.hasNext=n.hasNext,e.next=20;break;case 17:e.prev=17,e.t0=e["catch"](0),t.$log.error(e.t0);case 20:return e.prev=20,t.list.loading=!1,e.finish(20);case 23:case"end":return e.stop()}}),e,null,[[0,17,20,23]])})))()},handleListOptions:function(){var t=this;m.Z.option.listAsMapViewByKeys(["journals_page_size","journals_title"]).then((function(e){t.optionModal.options=e.data}))},handleQuery:function(){this.handlePageChange(1)},handleResetParam:function(){this.list.params.keyword=void 0,this.list.params.type=void 0,this.handlePageChange(1)},handleOpenPublishModal:function(){this.form.visible=!0,this.form.model={sourceContent:"",content:""}},handleOpenEditModal:function(t){this.form.model=(0,p.I8)(t),this.form.isPublic="INTIMATE"!==t.type,this.form.visible=!0},handleDelete:function(t){var e=this;m.Z.journal["delete"](t).finally((function(){e.handleListJournals()}))},handleOpenJournalCommentsDrawer:function(t){this.list.selected=t,this.journalCommentDrawer.visible=!0},onContentChange:function(t){var e=t.originalContent,a=t.renderContent;this.form.model.sourceContent=e,this.form.model.content=a},handleJournalTypeUpdate:function(t){var e=this;this.form.model=(0,p.I8)(t),this.form.model.type="PUBLIC"===t.type?"INTIMATE":"PUBLIC",m.Z.journal.update(this.form.model.id,this.form.model).catch((function(t){e.$log.error(t)})).finally((function(){e.handleListJournals()}))},handleSaveOrUpdate:function(){var t=this;t.$refs.journalForm.validate((function(e){e&&(t.form.model.type=t.form.isPublic?"PUBLIC":"INTIMATE",t.form.model.keepRaw=!0,t.form.saving=!0,t.form.model.id?m.Z.journal.update(t.form.model.id,t.form.model).catch((function(){t.form.saveErrored=!0})).finally((function(){setTimeout((function(){t.form.saving=!1}),400)})):m.Z.journal.create(t.form.model).catch((function(){t.form.saveErrored=!0})).finally((function(){setTimeout((function(){t.form.saving=!1}),400)})))}))},handleSaveOrUpdateCallback:function(){this.form.saveErrored?this.form.saveErrored=!1:(this.form.isPublic=!0,this.form.visible=!1,this.handleListJournals())},handlePageChange:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.list.params.page=t-1,this.handleListJournals()},handlePageSizeChange:function(t,e){this.$log.debug("Current: ".concat(t,", PageSize: ").concat(e)),this.list.params.page=0,this.list.params.size=e,this.handleListJournals()},onJournalCommentsDrawerClose:function(){this.form.model={},this.journalCommentDrawer.visible=!1,this.handleListJournals()},handleSaveOptions:function(){var t=this;m.Z.option.saveMapView(this.optionModal.options).then((function(){t.$message.success("保存成功！"),t.optionModal.visible=!1})).finally((function(){t.handleListOptions(),t.refreshOptionsCache()}))},handleSelectPrevious:function(){var t=this;return(0,o.Z)(regeneratorRuntime.mark((function e(){var a;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:if(a=t.list.data.findIndex((function(e){return e.id===t.list.selected.id})),!(a>0)){e.next=4;break}return t.list.selected=t.list.data[a-1],e.abrupt("return");case 4:if(0!==a||!t.list.hasPrevious){e.next=9;break}return t.list.params.page--,e.next=8,t.handleListJournals();case 8:t.list.selected=t.list.data[t.list.data.length-1];case 9:case"end":return e.stop()}}),e)})))()},handleSelectNext:function(){var t=this;return(0,o.Z)(regeneratorRuntime.mark((function e(){var a;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:if(a=t.list.data.findIndex((function(e){return e.id===t.list.selected.id})),!(a<t.list.data.length-1)){e.next=4;break}return t.list.selected=t.list.data[a+1],e.abrupt("return");case 4:if(a!==t.list.data.length-1||!t.list.hasNext){e.next=9;break}return t.list.params.page++,e.next=8,t.handleListJournals();case 8:t.list.selected=t.list.data[0];case 9:case"end":return e.stop()}}),e)})))()}})},v=h,g=a(70739),b=(0,g.Z)(v,n,i,!1,null,null,null),y=b.exports}}]);