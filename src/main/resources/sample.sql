
-- Create sample bucket with sample password
-- Add view on sample 
DocumentName: book
ViewName: all
Map:
function (doc, meta) {
  if(doc._class=="com.sample.data.model.Book"){
  emit(meta.id, doc);
  }
}

DocumentName: book
ViewName: byName
Map:
function (doc, meta) {
  if(doc._class == "com.sample.data.model.Book" ) {    
    emit(doc.bookName, doc);
  }
}

CREATE PRIMARY INDEX `sample-primary-index` ON `sample` USING GSI;
